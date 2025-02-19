#!/usr/bin/env python3
"""
OpenAI API 
"""

import os
import dataclasses
import asyncio
import backoff

import openai

from .language_model import LM, LMConfig


@dataclasses.dataclass
class GPTConfig(LMConfig):
    """
    Configuration for GPT API
    """
    _target_: str = "jam.language_models.GPT"
    api_key_env: str = "OPENAI_API_KEY"
    temperature: float = 1.0
    run_async: bool = False

class GPT(LM):
    """
    Calling the OpenAI GPT models via API
    """
    def __init__(self,model_name: str, max_token: int, stop_list: list[str], temperature: float, api_key_env:str , run_async: bool=False) -> None:

        access_token = os.getenv(api_key_env)

        openai.OpenAI.api_key = access_token

        self.model_name = model_name
        self.temperature = temperature
        self.max_token = max_token
        self. stop_list = stop_list

        self.run_async = run_async

        self.client = openai.OpenAI
        self.async_client = openai.AsyncOpenAI

        self.create_fn = backoff.on_exception(backoff.expo, openai.RateLimitError, max_time=100, jitter=backoff.full_jitter,)( lambda messages, client : client.chat.completions.create(messages=messages, model=self.model_name, max_tokens=self.max_token, temperature=self.temperature, stop=self.stop_list))

    def get_chat_template(self, )-> dict:
        return {"start_task":"{\"role\": \"system\", \"content\" : \"","end_task":"\"}",
                "start_example":"{\"role\": \"user\", \"content\" : \"", "end_example":"\"}",
                "start_answer":"{\"role\": \"assistant\", \"content\" : \"", "end_answer":"\"}",
                "seperator": ","}

    async def _create_async(self,prompt, client):
        response = await self.create_fn(m=prompt, client=client)
        return response.choices[0].message.content
    async def _generate_async(self, prompt: list[dict]):
        async with self.async_client() as client:
            return await asyncio.gather(*[self._create_async(p, client) for p in prompt])

    def generate(self, prompt: str | dict | list[str | dict]) -> list[str]:
        if isinstance(prompt, str): # single string
            prompt = [prompt,]

        if isinstance(prompt[0],dict):# single chat prompt
            prompt = [prompt, ]
        elif isinstance(prompt[0],str): # batch of strings
            prompt =[{"role":"user", "content":p} for p in prompt]


        if self.run_async:
            return asyncio.run(self._generate_async(prompt))
        client = self.client()
        return [self.create_fn(messages = p, client=client).choices[0].message.content for p in prompt]
