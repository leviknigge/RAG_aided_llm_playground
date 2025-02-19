#!/usr/bin/env python3
"""
Logic LM Parser
"""

import dataclasses
import re

import antlr4


from .. import basesolver

from .antlr.tptpParser import tptpParser
from .antlr.tptpLexer import tptpLexer

from . import translator



@dataclasses.dataclass
class NoneParserConfig(basesolver.ParserConfig):
    """
    TPTP Parser config
    """
    _target_: str = "jam.symbolic_solvers.tptp.NoneParser"

class NoneParser(basesolver.Parser):
    """
    Parser of TPTP format

    """
    def __init__(self, ) -> None:
        """
        convert: if the input should be convert from FOL to TPTP or the template structure be removed
        """
        super().__init__()
        # regex depends on prompt, make sure that they fit to prompt
        self.block_regex = re.compile(r"(?P<text>.+)",re.DOTALL)

    def encode(self, input_string: str)-> tuple[str, tptpParser.StartContext] :
        input_stream = antlr4.InputStream(input_string)
        lexer = tptpLexer(input_stream)
        stream = antlr4.CommonTokenStream(lexer)
        parser = tptpParser(stream)
        # parser._errHandler = antlr4.error.ErrorStrategy.BailErrorStrategy()
        parser.removeErrorListeners()
        parser.addErrorListener(translator.ErrorListener())
        tree = parser.start()

        tptp_result = input_string

        return tptp_result, tree

    def extract_structure(self, input_text: str) -> dict:
        """
        Extract the structure of the input text
        """
        # remove template stucture
        block_match = self.block_regex.search(input_text)
        if block_match:
            extracted_text = input_text.removesuffix("----")
        else:
            raise basesolver.ExtractException
        return {"extract": extracted_text}

    def __call__(self, input_dict: dict) -> dict:
        """
        parse a block prompt 
        
        """
        input_text = input_dict["extract"]
        # convert format from FOL to TPTP
        try:
            tptp_problem, parse_ast = self.encode(input_text)
        except antlr4.error.Errors.CancellationException as e:
            error_message= e.args[0]
            raise basesolver.ParseException(message=error_message) from e
        except RecursionError as e:
            error_message = "Solution too long. Try to remove unnecessary parts."
            raise basesolver.ParseException(message=error_message) from e

        return tptp_problem, parse_ast
