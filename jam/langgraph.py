#!/usr/bin/env python3
"""Pipeline for autoformalization using LangGraph
"""

import logging
import dataclasses
import typing
import functools

import jinja2
import mlflow
import torch
import langgraph
import langgraph.graph

from . import language_models
from . import symbolic_solvers
from . import pipeline
from . import utils


class GraphState(typing.TypedDict):
    """
    State of autoformalization pipeline
    """
    context: str
    query: str
    prediction: bool
    iterations: int
    generations: list[str]
    tool_responses: list[symbolic_solvers.ToolState]


@dataclasses.dataclass
class LangGraphWrapperConfig(pipeline.PipelineConfig):
    """
    configurations of autoformalization pipeline with LangGraph
    """
    lm: language_models.LMConfig = dataclasses.MISSING
    tool: symbolic_solvers.ToolPipelineConfig = dataclasses.MISSING
    _target_: str = "jam.langgraph.LangGraphWrapper"
    prompt_file: str = "inline.txt"
    feedback_file: typing.Optional[str] = None
    use_chat_prompt: bool = False
    max_iterations: int = 3

class LangGraphWrapper(pipeline.Pipeline):
    """
    Autoformalization pipeline using LangGraph

    additional references: https://langchain-ai.github.io/langgraph/tutorials/reflexion/reflexion/ 
    """

    def __init__(self, lm:language_models.LM, tool: symbolic_solvers.ToolPipeline, prompt_file: str, feedback_file: str, use_chat_prompt: bool, max_iterations: int) -> None:

        self.lm = lm
        self.tool = tool

        self.prompt_file= prompt_file
        self.feedback_file = feedback_file
        self.render_draft = None
        self.render_feedback = None
        self.use_chat_prompt = use_chat_prompt

        self.max_iterations = max_iterations

        workflow = langgraph.graph.StateGraph(GraphState)

        # Define the nodes
        workflow.add_node("draft", self.generate_draft)
        workflow.add_node("revise", self.generate_revision)
        workflow.add_node("execute_tools", self.call_tool)

        # Build graph
        workflow.add_edge(langgraph.graph.START, "draft")
        workflow.add_edge("draft", "execute_tools")
        workflow.add_conditional_edges("execute_tools", self.decide_to_finish, { "end": langgraph.graph.END, "revise": "revise",},)
        workflow.add_edge("revise", "execute_tools")

        self.app = workflow.compile()

        logging.debug(self.app.get_graph(xray=1).draw_mermaid())

    def generate_draft(self, state: GraphState) -> GraphState:
        """
        Generate the first draft with the LLM
        """
        context  = state["context"]
        query = state["query"]

        assert self.render_draft is not None
        input_texts = [self.render_draft(context=context, query=query),]

        # generate general text
        generated_texts = mlflow.trace(self.lm.generate,span_type="LLM")(input_texts)[0] # LLM generates list, but intput is only one

        # add first generation to generations
        return {"iterations": 1 ,"generations": [generated_texts,]}

    def call_tool(self, state: GraphState) -> GraphState:
        """
        Invoke the tool pipeline
        """

        context =state["context"]
        query = state["query"]
        generated_text = state["generations"][-1]
        tool_responses =  state["tool_responses"] if "tool_responses" in state else []

        tool_responses.append(self.tool(generated_text, context=context, query=query))

        # idea: use newest valid predictions instead of simple fallback
        prediction = tool_responses[-1]["prediction"] if "prediction" in tool_responses[-1] else tool_responses[-1]["fallback"]

        return {"prediction": prediction, "tool_responses":tool_responses}

    def generate_revision(self, state: GraphState) -> GraphState:
        """
        Generate a candiate logical form with the LLM
        """
        context  = state["context"]
        query = state["query"]
        generations = state["generations"]

        iterations= state["iterations"]
        feedback_text = state["tool_responses"][-1]["feedback"]

        # TODO check if parameter are enough
        input_texts = self.render_feedback(context=context, query=query, candidate_lf=generations[-1], feedback_message=feedback_text)

        # generate general text
        generated_texts = mlflow.trace(self.lm.generate,span_type="LLM")(input_texts)[0] # LLM generates list, but intput is only one

        # add new generation to end of generations
        generations.append(generated_texts)

        return {"iterations": iterations+1 ,"generations": generations}

    def decide_to_finish(self, state: GraphState):
        """
        Determines if another iteration is required to get a prediction
        """
        latest_status = state["tool_responses"][-1]["status"]
        iterations = state["iterations"]

        if latest_status == symbolic_solvers.JAMStatus.OK.name or iterations >= self.max_iterations:
            return "end"
        return "revise"

    def load_prompt(self, prompt_dir: str= None ) -> None:
        """
        Load the prompt template 
        """
        prompt_env = jinja2.Environment(loader = jinja2.FileSystemLoader(prompt_dir), undefined=jinja2.StrictUndefined)
        prompt_template = prompt_env.get_template(self.prompt_file)

        chat_command = self.lm.get_chat_template()
        def render(template: jinja2.Template, **kwargs):
            # handle "
            kwargs = {k:v.replace("\\","\\\\").replace("\"","\\\"") for k,v in kwargs.items()}
            prompt_str = template.render(**(kwargs | chat_command), use_chat=self.use_chat_prompt)
            return utils.convert_json_prompt(prompt_str) if self.use_chat_prompt else prompt_str
        self.render_draft = functools.partial(render, template=prompt_template)
        if self.feedback_file:
            feedback_template = prompt_env.get_template(self.feedback_file)
        else:
            feedback_template = prompt_template
        self.render_feedback = functools.partial(render, template=feedback_template)

    def predict(self, context: str | list[str], query: str | list[str]) -> list[bool]:
        """
        Predictions 
        Params: 
        """

        if not isinstance(context,list):
            context = [context,]
        if not isinstance(query, list):
            query = [query,]
        assert len(context) == len(query)

        # TODO loop over batch async and call LLM async + queue to collect batch, requires dynamic call from evaluation
        predictions = []
        states = []
        for c,q in zip(context,query):
            with mlflow.start_span(name="Prediction", span_type="CHAIN") as span:
                span.set_inputs({"contexts":c, "queries": q})

                result = self.app.invoke({"context": c, "query": q, })

                predictions.append(result["prediction"])
                iterations = [{"generated text": gen,
                               "preliminary prediction": tool["prediction"] if "prediction" in tool else tool["fallback"],
                               "problem status": tool["status"],
                               "feedback": tool["feedback"]} for gen,tool in zip(result["generations"],result["tool_responses"])]
                states.append({"status": result["tool_responses"][-1]["status"], "iterations":result["iterations"],"feedback":iterations,})

                span.set_outputs({"prediction": result})

        return torch.tensor(predictions), states
