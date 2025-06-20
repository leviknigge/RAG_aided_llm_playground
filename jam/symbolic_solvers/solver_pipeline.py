#!/usr/bin/env python3
"""
Solver pipeline
"""

import dataclasses
# import enum
import typing
import logging

import mlflow

from . import basesolver

class CriticFeedback(typing.TypedDict):
    """
    Feedback of critics 
    """
    source:  type
    message: str

# subclass of langgraph.GenerationState
class SolverState(basesolver.ToolState):
    """
    State of autoformalization pipeline
    """
    ast: typing.Optional[str]# TODO switch to FOLParser from fol_parser.folParser file
    formula: typing.Optional[str]# TODO change to right type
    problems: list[CriticFeedback]
    # problem_count: collections.Counter
    feedback: str
    status: basesolver.JAMStatus

@dataclasses.dataclass
class SolverPipelineConfig(basesolver.ToolPipelineConfig):
    """
    Configuration for Solver
    """
    parser: basesolver.ParserConfig = dataclasses.MISSING
    solver: basesolver.SolverConfig = dataclasses.MISSING
    linter: typing.Optional[basesolver.LinterConfig] = None
    tests: typing.Optional[list[basesolver.TestConfig]] = None
    _target_: str = "jam.symbolic_solvers.SolverPipeline"


class SolverPipeline(basesolver.ToolPipeline):
    """
    Tool for autoformalization check 
    """
    def __init__(self, parser: basesolver.ParserConfig, solver: basesolver.SolverConfig, linter: basesolver.LinterConfig, tests: list[basesolver.TestConfig], ) -> None:

        self.parser = parser
        self.solver = solver
        self.linter = linter
        self.tests = tests

        self.status_map = { basesolver.Parser: basesolver.JAMStatus.PARSE, basesolver.Solver: basesolver.JAMStatus.RUNTIME, basesolver.Linter :basesolver.JAMStatus.WARNING, basesolver.Test : basesolver.JAMStatus.WARNING}


    def __call__(self, generated_text: str, **kwargs) -> SolverState:

        tool_state = self._parse(generated_text=generated_text)

        if "prediction" not in tool_state:
            tool_state["fallback"] = False

        problems = tool_state["problems"] if "problems" in tool_state else []

        if "ast" in tool_state and self.linter:
            problems = problems + self._lint(tool_state["ast"], **kwargs)
        if "formula" in tool_state and self.tests:
            problems = problems + self._test(tool_state["formula"])

        tool_state["problems"] = problems

        tool_state["feedback"] = self._merge_feedback(problems)

        status_list = []
        for p in tool_state["problems"]:
            t = [v for k,v in self.status_map.items() if issubclass(p["source"],k)]
            assert len(t) == 1
            status_list += t

        tool_state["status"] = max(status_list).name if len(status_list) > 0 else basesolver.JAMStatus.OK.name

        return tool_state


    def _parse(self, generated_text: str) -> SolverState:
        """
        Parse the provided solution
        """
        try:
            # TODO move template match out of parser in separate function
            candidate_solution = mlflow.trace(self.parser.extract_structure, span_type="PARSER")(generated_text)
        except basesolver.ExtractException as _:
            logging.debug("Extraction error")
            feedback = CriticFeedback(source=type(self.parser), message="Extraction error")
            return {"problems": [feedback,]}

        try:
            formula, ast = mlflow.trace(self.parser.__call__, name="parser", span_type="PARSER")(candidate_solution)
        except basesolver.ParseException as e:
            logging.debug("Parse error")
            backprompt = e.message if e.message else "Parsing Error"
            feedback = CriticFeedback(source=type(self.parser), message=backprompt)
            return {"problems": [feedback]}

        try:
            solver_response = mlflow.trace(self.solver.__call__, name="solver", span_type="TOOL")(formula)
        except basesolver.SolverRuntimeException as what:
            logging.debug("Runtime error")
            backprompt = "Runtime error" # TODO include runtime error message here
            feedback = CriticFeedback(source=type(self.solver), message=backprompt)
            return {"ast": ast, "problems": [feedback,]}

        prediction = solver_response
        return {"prediction": prediction, "ast": ast, "formula": formula, }

    def _lint(self, tree: str, **kwargs) -> list[CriticFeedback]:
        """
        Runs static code analysis on the AST
        """


        warnings = mlflow.trace(self.linter.__call__, name="linter", span_type="PARSER")(tree, **kwargs)
        problems = [CriticFeedback(source=type(self.linter), message=w) for w in warnings]
        return problems

    def _test(self, formula: str) -> list[CriticFeedback]:
        """
        Runs all tests on the logical form
        """
        tested = [t(formula) for t in self.tests ]
        failed = [(type(self.tests[i]),t[1]) for i,t in enumerate(tested) if not t[0]]

        problems = [CriticFeedback(source=t, message=f) for t,f in failed]
        return problems

    def _merge_feedback(self, problems: list[CriticFeedback]) -> str:
        """
        Combine the gathered feedback
        """

        feedback = ' '.join([c["message"] for c in problems])

        # count problem types in feedback
        # TODO update counter
        # problem_count = collections.Counter([p["source"] for p in problems])

        # return feedback, problem_count
        return feedback
