#!/usr/bin/env python3
"""
Language Models uses in the augmented language model playground
"""

from abc import ABC
from abc import abstractmethod
import dataclasses

@dataclasses.dataclass
class LMConfig():
    """
    Basic configuration of a language model 
    """
    model_name: str = dataclasses.MISSING
    max_token: int = 1024
    stop_list: list[str] = dataclasses.field(default_factory=lambda: ["----\n",])


class LM(ABC):
    """
    Abstract base class for language model component

    """
    @abstractmethod
    def __init__(self, model_name: str, max_token: int , stop_list:list[str]) -> None:
        pass

    @abstractmethod
    def generate(self, prompt: str | dict | list[str|dict]) -> str:
        """
        Generates text based on the prompt input
        """

    def get_chat_template(self, )-> dict:
        """
        Return empty dict for chat templates
        """
        return {"start_task":"","end_task":"",
                "start_example":"", "end_example":"",
                "start_answer":"", "end_answer":"",
                "seperator": ""}
