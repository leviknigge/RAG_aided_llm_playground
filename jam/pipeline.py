#!/usr/bin/env python3
"""Interface for LLM prediction models for context-based binary question answering.
"""

from abc import ABC
from abc import abstractmethod
import dataclasses

@dataclasses.dataclass
class PipelineConfig():
    """
    Interface for configurations of LLM-based models
    """

class Pipeline (ABC):
    """
    Interface for LLM-based models
    """
    @abstractmethod
    def __init__(self) -> None:
        pass
    @abstractmethod
    def load_prompt(self, prompt_dir: str= None ) -> None:
        """
        Load a prompt 
        """
    @abstractmethod
    def predict(self, context: str | list[str], query: str | list[str]) -> list[bool]:
        """e prediction
        """
