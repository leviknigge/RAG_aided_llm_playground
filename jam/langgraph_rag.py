#!/usr/bin/env python3
"""
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

from sentence_transformers import SentenceTransformer
import json
import nltk
# nltk.download()

from . import language_models
from . import symbolic_solvers
from . import pipeline
from . import utils
import os


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
class LangGraphWrapperRagConfig(pipeline.PipelineConfig):
    """
    configurations of autoformalization pipeline with LangGraph
    """
    lm: language_models.LMConfig = dataclasses.MISSING
    tool: symbolic_solvers.ToolPipelineConfig = dataclasses.MISSING
    _target_: str = "jam.langgraph_rag.LangGraphRagWrapper"
    prompt_file: str = "inline.txt"
    feedback_file: typing.Optional[str] = None
    use_chat_prompt: bool = False
    max_iterations: int = 3

class LangGraphRagWrapper(pipeline.Pipeline):
    """
    Autoformalization pipeline using LangGraph and RAG

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


        self.rag_embedding = SentenceTransformer("all-MiniLM-L6-v2")
        self.kb_sents, self.kb_embeddings = self.load_kb()


        workflow = langgraph.graph.StateGraph(GraphState)

        workflow.add_node("rag_search", self.rag_search)

        # Define the nodes
        workflow.add_node("draft", self.generate_draft)
        workflow.add_node("revise", self.generate_revision)
        workflow.add_node("execute_tools", self.call_tool)

        # Build graph
        workflow.add_edge(langgraph.graph.START, "rag_search")
        workflow.add_edge("rag_search", "draft")
        workflow.add_edge("draft", "execute_tools")
        workflow.add_conditional_edges("execute_tools", self.decide_to_finish, { "end": langgraph.graph.END, "revise": "revise",},)
        workflow.add_edge("revise", "execute_tools")

        self.app = workflow.compile()

        # logging.debug(self.app.get_graph(xray=1).draw_mermaid())


    def load_kb(self):
        """
        Loads the knowledge base

        returns a list of sentences in the knowledge base and the embedded sentences.
        """
        root = r"/home/lknigge/augmented_llm_playground_fabian/data/LogicBench"
        kb_path = r"/abducted_data/binary/fol_eval_kb.jsonl"
        # root = r"/data/LogicBench"
        # kb_path = r"/abducted_data/binary/fol_eval_kb.jsonl"
        # root = r".\data\LogicBench"
        # kb_path = r"\abducted_data\binary\fol_eval_kb.jsonl"
        
        kb_sents = []

        # print(os.path.dirname(os.path.realpath(__file__)))
        # print(os.getcwd())
        # print("test")
        with open(root + kb_path, "r") as f:
            # print("test2")
            for line in f:
                datapoint = json.loads(line)
                id, sent = list(datapoint.items())[0]

                if not sent == []:
                    # kb_data[id] = sent
                    kb_sents.extend(sent)
                if sent == []:
                    kb_sents.extend([""])
                    # kb_data[id] = [""]

        kb_embeddings = self.rag_embedding.encode(kb_sents)
        return kb_sents, kb_embeddings

    def simple_retrieve_c(self, model, kb_embeddings, kb_sents, context):
        """
        Retrieves the top sentence from the knowledge base given a context

        returns top sentence
        """
        
        # model = SentenceTransformer("multi-qa-mpnet-base-cos-v1")

        # kb_embeddings = model.encode(kb_sents)
        
        query_embedding = model.encode(nltk.sent_tokenize(context))
        similarities = model.similarity(kb_embeddings, query_embedding)

        # max_similarities = torch.max(similarities, 1)
        # ranked_similarities = torch.sort(max_similarities.values)

        mean_similarities = torch.mean(similarities,1)
        ranked_similarities = torch.sort(mean_similarities)
        
        top_i = ranked_similarities.indices[-1]
        top_sent = kb_sents[top_i]

        return top_sent


    # rag/search function
    def rag_search(self, state:GraphState) -> GraphState:
        """
        Retrieve and add sentence from database to context
        """
        
        context  = state["context"]
        query = state["query"]

        x = context + " " + query
        top_sent = self.simple_retrieve_c(self.rag_embedding, self.kb_embeddings, self.kb_sents, x)


        # generate new context
        new_context = context + " " + top_sent

        return {"context": new_context, "query": query}


    def generate_draft(self, state: GraphState) -> GraphState:
        """
        Generate the first draft with the LLM
        """
        context  = state["context"]
        query = state["query"]

        assert self.render_draft is not None
        input_texts = [self.render_draft(context=context, query=query),]

        # generate general text
        # generated_texts = mlflow.trace(self.lm.generate,span_type="LLM")(input_texts)[0] # LLM generates list, but intput is only one
        generated_texts = self.lm.generate(input_texts)[0] # LLM generates list, but intput is only one
        # print("1234test")
        # generated_texts = '(∀ ?food (storesInFridge(?food) → remainsFreshLonger(?food)) # stores stuff in fridge\n∧ (keepsOutside(?food) → spoilsQuickly(?food))) ∧ (storesInFridge(john) ∨ ¬spoilsQuickly(jon))\n\nFirst-order logic question: remainsFreshLonger(john) ∨  ¬keepsOutside(john) # thisis a test comment\n----'

        # add new generation to end of generations

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

        input_texts = self.render_feedback(context=context, query=query, candidate_lf=generations[-1], feedback_message=feedback_text)
        # add the simple feedback prompt
        # TODO change to more complex feedback prompt
        input_texts = input_texts + [{"role": "assistant", "content":generations[-1]},{"role":"user", "content":f"{feedback_text}. Correct this mistake without adding anything else. \n Predicates:"}]

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
