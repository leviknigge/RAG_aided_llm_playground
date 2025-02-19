#!/usr/bin/env python3
"""
Wrapper for Prover9 reasoner
"""
import dataclasses
import pathlib
import os

import nltk
# from nltk.sem.logic import NegatedExpression

from jam.symbolic_solvers import basesolver

from .fol_prover9_parser import Prover9_FOL_Formula
from .fol_formula import FOL_Formula

@dataclasses.dataclass
class Prover9ParserConfig(basesolver.ParserConfig):
    """
    TPTP Parser config
    """
    _target_: str = "jam.symbolic_solvers.logic_lm.fol.fol_solver.Prover9Parser"

class Prover9Parser(basesolver.Parser):
    """
    Parser for Logic-LM
    """
    def __init__(self, ) -> None:
        super().__init__()

    def extract_structure(self, input_text: str) -> dict:
        # remove template structure
        try:
            # Split the string into premises and conclusion
            premises_string = input_text.split("Conclusion:")[0].split("Premises:")[1].strip()
            conclusion_string = input_text.split("Conclusion:")[1].strip()

        except Exception as e: # TODO fix
            raise basesolver.ExtractException from e

        return {"premises": premises_string, "conclusion": conclusion_string}


    def __call__(self, input_dict: dict) -> dict:
        """
        parse a block prompt 
        """
        premises_string = input_dict["premises"]
        conclusion_string = input_dict["conclusion"]

        try:
            # Extract each premise and the conclusion using regex
            premises = premises_string.strip().split('\n')
            conclusion = conclusion_string.strip().split('\n')

            logic_premises = [premise.split(':::')[0].strip() for premise in premises]
            logic_conclusion = conclusion[0].split(':::')[0].strip()

            # convert to prover9 format
            prover9_premises = []
            for premise in logic_premises:
                fol_rule = FOL_Formula(premise)
                if not fol_rule.is_valid:
                    raise basesolver.ParseException(message="parsing error")
                prover9_rule = Prover9_FOL_Formula(fol_rule)
                prover9_premises.append(prover9_rule.formula)

            fol_conclusion = FOL_Formula(logic_conclusion)
        except Exception as e:
            error_message = "parsing error"
            raise basesolver.ParseException(message=error_message) from e

        if not fol_conclusion.is_valid:
            error_message = "parsing error"
            raise basesolver.ParseException(message=error_message)
        try:
            prover9_conclusion = Prover9_FOL_Formula(fol_conclusion).formula
        except Exception as e:
            error_message = "parsing error"
            raise basesolver.ParseException(message=error_message) from e

        return {"conclusion": prover9_conclusion, "premises": prover9_premises}, None

@dataclasses.dataclass
class Prover9ReasonerConfig(basesolver.SolverConfig):
    """
    Vampire reasoner config
    """
    _target_: str = "jam.symbolic_solvers.logic_lm.fol.fol_solver.Prover9Reasoner"
    timeout: int = 10

class Prover9Reasoner(basesolver.Solver):
    """
    Wrapper for Prover9 (NLTK) reasoning 
    """
    def __init__(self, timeout:int = 10) -> None:
        super().__init__()

        self.timeout = timeout

        # set the path to the prover9 executable
        file_dir = pathlib.Path(__file__).parent.parent.parent.resolve()
        os.environ['PROVER9'] = str(file_dir / "Prover9/bin/bin")

    def __call__(self, input_obj: dict) -> bool:
        """
        param: input_obj 
        """
        prover9_conclusion = input_obj["conclusion"]
        prover9_premises = input_obj["premises"]
        try:
            goal = nltk.inference.prover9.Expression.fromstring(prover9_conclusion)
            assumptions = [nltk.inference.prover9.Expression.fromstring(a) for a in prover9_premises]

            prover = nltk.inference.prover9.Prover9Command(goal, assumptions, timeout=self.timeout)
            result = prover.prove()
        except Exception as e: # TODO fix to more narrow exception
            raise basesolver.SolverRuntimeException(message=str(e)) from e

        return result
