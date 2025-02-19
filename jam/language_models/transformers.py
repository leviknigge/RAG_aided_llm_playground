#!/usr/bin/env python3
"""
Hugging Face Transformers Wrapper

"""
import dataclasses
import logging
import os
import re

import torch
import transformers

from .language_model import LM, LMConfig

class StopTokenCriteria(transformers.StoppingCriteria):
    '''
    Stopping on token ids with custom stopping criteria for HuggingFace Transfomers 
    '''
    def __init__(self, stop_token_ids: torch.LongTensor) -> None:
        super().__init__()
        self.stop_token_ids = stop_token_ids

    def __call__(self, input_ids: torch.LongTensor, scores: torch.FloatTensor, **kwargs) -> bool:
        for stop_ids in self.stop_token_ids:
            if torch.eq(input_ids[0][-len(stop_ids):], stop_ids).all():
                return True
        return False

@dataclasses.dataclass
class TransformersWrapperConfig(LMConfig):
    """
    Configuration for LlaMA models 
    """
    _target_: str = "jam.language_models.TransformersWrapper"
    api_key_env: str = "HUGGINGFACE_KEY"
    use_quantization: bool = True
    device: str = ""

class TransformersWrapper(LM):
    """
    Wrapper for all transformers.AutoModelForCausalLM language models.
    """
    def __init__(self, model_name: str, max_token: int, use_quantization: bool, stop_list: list[str], device: str, api_key_env: str, ) -> None:
        """
        Initializes the language model.
        
        """
        access_token = os.getenv(api_key_env)
        if not device:
            device = "cuda" if torch.cuda.is_available() else "cpu"
        if device != "cuda":
            logging.warning("Running on CPU")
        self.device = device

        self.max_length = max_token

        if self.device != "cpu" and use_quantization:
            quantization_config = transformers.BitsAndBytesConfig(load_in_4bit=True, bnb_4bit_use_double_quant=True, bnb_4bit_quant_type="nf4", bnb_4bit_compute_dtype=torch.bfloat16)
            # TODO Quick fix: Gemma models fail if you set torch_dtype to bfloat16
            if model_name in ["google/gemma-2-2b-it","google/gemma-2-9b-it"]:
                self.model = transformers.AutoModelForCausalLM.from_pretrained(model_name,token=access_token, quantization_config= quantization_config, use_safetensors=True, device_map=self.device)
            else:
                self.model = transformers.AutoModelForCausalLM.from_pretrained(model_name,token=access_token, quantization_config= quantization_config, use_safetensors=True, torch_dtype=torch.bfloat16, device_map=self.device)
        else:
            quantization_config = None
            self.model = transformers.AutoModelForCausalLM.from_pretrained(model_name,token=access_token, quantization_config= quantization_config, use_safetensors=True, device_map=self.device)


        self.tokenizer = transformers.AutoTokenizer.from_pretrained(model_name,token=access_token, device=self.device)
        self.tokenizer.pad_token = self.tokenizer.eos_token
        self.tokenizer.padding_side="left" # required for prompt removal

        self.stop_regex = re.compile(f'^(?P<generated_text>.*)(?:{'|'.join(map(re.escape,stop_list))})$', re.DOTALL)
        stop_token_ids = [self.tokenizer(stop_string, add_special_tokens=False, return_tensors="pt").input_ids.to(self.device).flatten() for stop_string in stop_list]

        self.stop_token_ids = [token for token in stop_token_ids if len(token)==1]
        self.stop_sequences = [token for token in stop_token_ids if len(token)>1]

        if len(self.stop_sequences):
            self.stopping_criteria = transformers.StoppingCriteriaList([StopTokenCriteria(self.stop_sequences)])
        else:
            self.stopping_criteria= None

    def get_chat_template(self, )-> dict:
        return {"start_task":"{\"role\": \"user\", \"content\" : \"","end_task":"\"}",
                "start_example":"{\"role\": \"user\", \"content\" : \"", "end_example":"\"}",
                "start_answer":"{\"role\": \"assistant\", \"content\" : \"", "end_answer":"\"}",
                "seperator": ","}

    def _remove_stop_list(self, responses):
        """
        remove stop token id and stop sequences
        """
        def replace_end(r):
            m = self.stop_regex.match(r)
            if m:
                return m.group("generated_text")
            else:
                return r
        return [replace_end(r) for r in responses]

    def generate(self, prompt: str | dict | list[str|dict]) -> list[str]:
        """
        generates texts based on the given prompts.
        """
        # TODO this function must contain a bug for some models (in some cases)
        if isinstance(prompt, str): # single string
            prompt = [prompt,]

        if isinstance(prompt[0],dict):# single chat prompt
            prompt = [prompt, ]
        elif isinstance(prompt[0],str): # batch of strings
            prompt =[{"role":"user", "content":p} for p in prompt]

        # tokenize
        if isinstance(prompt[0],str): # Is the prompt a normal string or a chat template (dict)?
            input_dict = self.tokenizer(prompt, padding=True, return_tensors="pt")
        else:
            input_dict = self.tokenizer.apply_chat_template(prompt, tokenize=True, add_generation_prompt=True, padding=True, return_dict=True, return_tensors="pt").to(self.device)
        input_dict = {k: v.to(self.device) for k, v in input_dict.items()}


        output = self.model.generate(**input_dict, do_sample=True, max_new_tokens=self.max_length, pad_token_id=self.tokenizer.eos_token_id, eos_token_id=[self.tokenizer.eos_token_id, *self.stop_token_ids], stopping_criteria=self.stopping_criteria)

        # just removing the padded input_ids does not work!
        # inputs_dict["input_ids"].shape[1] only works for left padding!
        response = self.tokenizer.batch_decode(output[:,input_dict["input_ids"].shape[1]:], skip_special_tokens=True)

        return self._remove_stop_list(response)
        # return responses
