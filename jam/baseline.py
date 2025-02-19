#!/usr/bin/env python3
"""
"""

import logging
import dataclasses
import re
import random
import jinja2

import mlflow
import torch

from . import language_models
from . import symbolic_solvers # required to handle extraction errors
from . import pipeline
from . import utils


@dataclasses.dataclass
class LMPipelineConfig(pipeline.PipelineConfig):
    """
    LM baseline conifg
    """
    lm: language_models.LMConfig = dataclasses.MISSING
    _target_: str = "jam.baseline.LMPipeline"
    prompt_file: str = "inline.txt"
    use_chat_prompt: bool = False
    use_cot: bool = False
    # TODO add n_samples as parameter

def parse_prompt(parse_regex: re.Pattern, generated_text: str) -> int:
    """
    parse 
    """
    generated_text = generated_text.lower()
    answer_match = parse_regex.match(generated_text)

    if answer_match and answer_match.group("answer")=="yes":
        return 1
    elif answer_match and answer_match.group("answer")=="no":
        return 0
    else:
        raise symbolic_solvers.ExtractException

class LMPipeline (pipeline.Pipeline):
    """
    Language Model pipeline
    """
    def __init__(self, lm:language_models.LM, prompt_file: str, use_chat_prompt: bool, use_cot: bool) -> None:
        """
        Init 
        """
        self.lm = lm

        self.prompt_file = prompt_file
        self.render_prompt = None
        self.use_chat_prompt = use_chat_prompt
        self.use_cot = use_cot

        self.generated_texts = []
        self.status = []

        if self.use_cot:
            self.answer_mapper = lambda x: parse_prompt(re.compile(r"(.*)answer\:\s*(?P<answer>(yes)|(no))(.*)",re.DOTALL),x)
        else:
            self.answer_mapper = lambda x: parse_prompt(re.compile(r"(.*)(?P<answer>(yes)|(no))(.*)",re.DOTALL),x)

    def load_prompt(self, prompt_dir: str= None ) -> None:
        """
        Load the prompt template 
        """
        prompt_env = jinja2.Environment(loader = jinja2.FileSystemLoader(prompt_dir), undefined=jinja2.StrictUndefined)
        prompt_template = prompt_env.get_template(self.prompt_file)

        chat_command = self.lm.get_chat_template()
        def render_prompt(**kwargs):
            # handle '"' and '/' symbols by escaping them before converting to JSON
            kwargs = {k:v.replace("\\","\\\\").replace("\"","\\\"") for k,v in kwargs.items()}
            # TODO add n_samples as hyperparameter to the model
            prompt_str = prompt_template.render(**(kwargs | chat_command), use_chat=self.use_chat_prompt, use_cot=self.use_cot)
            return utils.convert_json_prompt(prompt_str) if self.use_chat_prompt else prompt_str
        self.render_prompt = render_prompt

    def predict(self, context: list[str], query: list[str]) -> list[bool]:
        """
        Infer the results based on the input text
        """

        batch_size = len(query)

        with mlflow.start_span(name="Prediction", span_type="LLM") as span:
            span.set_inputs({"contexts":context, "queries": query})
            span.set_attributes({"batch_size":batch_size})

            # clear old values
            self.status = [symbolic_solvers.JAMStatus.OK] * batch_size

            predictions = torch.zeros(batch_size, dtype=torch.bool)
            states = []

            assert self.render_prompt is not None
            input_texts = [self.render_prompt(context=c, query=q) for c,q in zip(context, query)]

            # generate general text
            self.generated_texts = mlflow.trace(self.lm.generate, span_type="LLM")(input_texts)

            for i,generated_text in enumerate(self.generated_texts):
                # extract formalized rules from text
                with mlflow.start_span(name="Answer Mapping",span_type="PARSER") as p_span:
                    p_span.set_inputs({"batch sample no":i, "generated text": generated_text})
                    try:
                        predictions[i] = self.answer_mapper(generated_text)
                        states.append({"status": symbolic_solvers.JAMStatus.OK.name, "llm_generation": generated_text})
                    except symbolic_solvers.ExtractException as _:
                        logging.debug("Extraction error")
                        states.append({"status": symbolic_solvers.JAMStatus.PARSE.name, "llm_generation": generated_text})
                        #fallback strategy
                        predictions[i] = 0
                    p_span.set_outputs({"prediction":predictions[i],} | states[-1])

            span.set_outputs({"prediction":predictions, "status":states})

        return predictions, states

@dataclasses.dataclass
class RandomPipelineConfig(pipeline.PipelineConfig):
    _target_: str = "jam.baseline.RandomPipeline"
    weights: list[float] = dataclasses.field(default_factory= lambda: [0.5,0.5])

class RandomPipeline (pipeline.Pipeline):
    """
    random model
    """
    def __init__(self, weights: list[float]) -> None:
        self.options = range(len(weights))
        self.weights = weights
        self.status = []

    def load_prompt(self, prompt_dir: str = None) -> None:
        pass

    def predict(self, context: list[str], query: list[str]) -> list[bool]:
        batch_size = len(query)

        return torch.tensor(random.choices(self.options, self.weights, k=batch_size)), {"error": [symbolic_solvers.JAMStatus.OK] * batch_size}
