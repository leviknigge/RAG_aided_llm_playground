#!/usr/bin/env python3
"""
Collection of all external solver
"""

from abc import ABC
from abc import abstractmethod
import dataclasses
import typing
import enum

class JAMStatus(enum.IntEnum):
    """
    Error enum for the predictions of JAM
    """
    OK = 0
    # EXTRACT = -1
    PARSE = -2
    RUNTIME = -3
    WARNING = -4

class ExtractException(Exception):
    """Raise for errors during extraction"""

class ParseException(Exception):
    """Raise for errors during parsing"""
    def __init__(self, message: str, *args: object) -> None:
        super().__init__(*args)

        self.message = message

class SolverRuntimeException(Exception):
    """Raise for errors during solver run"""
    def __init__(self, *args: object, message: str = "") -> None:
        super().__init__(*args)

        self.message = message


@dataclasses.dataclass
class ParserConfig():
    """
    Parser config
    """
    _target_: str = "jam.symbolic_solvers.Parser"

class Parser(ABC):
    """
    Interface for all parsers used in LLM-Modulo pipeline 
    """

    @abstractmethod
    def extract_structure(self, input_text: str) -> dict:
        """
        Extract the required structure from texts
        """
    @abstractmethod
    def __call__(self, input_dict: dict) -> dict:
        """
        Parse the input 
        """

@dataclasses.dataclass
class LinterConfig():
    """
    Linter Config
    """
    _target_: str = "jam.symbolic_solvers.Linter"

class Linter(ABC):
    """
    Interface for linter.
    """
    @abstractmethod
    def __call__(self, ast , **kwargs) -> list[dict]:
        """
        Produce linter warnings for the given AST
        """

@dataclasses.dataclass
class TestConfig():
    """
    Test Config
    """
    _target_: str = "jam.symbolic_solvers.Test"
class Test(ABC):
    """
    Interface for test.
    """
    @abstractmethod
    def __call__(self, program: dict) -> bool:
        pass

@dataclasses.dataclass
class SolverConfig():
    """
    Solver config
    """
    _target_: str = "jam.symbolic_solvers.Solver"

class Solver(ABC):
    """
    Interface for all solver.
    """
    @abstractmethod
    def __call__(self, input_obj: dict) -> str:
        """
        Evaluates the given input_string.
        """

class ToolState(typing.TypedDict):
    """
    State of the tool pipeline
    """
    prediction: bool
    fallback: bool

@dataclasses.dataclass
class ToolPipelineConfig():
    """
    Configuration of tool
    """
    # _target_: str = "jam.symbolic_solvers.ToolPipeline"

class ToolPipeline(ABC):
    """Interface solver pipeline
    """
    @abstractmethod
    def __call__(self, generated_text: str) -> ToolState:
        """
        Tool call
        """
