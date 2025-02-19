#!/usr/bin/env python3
"""
A set of analysis tools for FOL grammar
"""

import antlr4
import antlr4.tree


# from .antlr.folParser import folParser
# from .antlr.folLexer import folLexer
from antlr_simplified.folParser import folParser as fol_simplifiedParser
from antlr_simplified.folLexer import folLexer as fol_simplifiedLexer

class FindNegation():

    def __init__(self,simplified=True):

        if simplified:
            self.lexer = fol_simplifiedLexer
            self.parser = fol_simplifiedParser
        # else:
        #     self.lexer = folLexer
        #     self.parser = folParser


    def __call__(self, input_program: str):

        input_stream = antlr4.InputStream(input_program)
        lexer = self.lexer(input_stream)
        stream = antlr4.CommonTokenStream(lexer)
        parser = self.parser(stream)
        # TODO Do I need a ErrorListner here?
        # parser._errHandler = antlr4.error.ErrorStrategy.BailErrorStrategy()
        tree = parser.start()

        names = antlr4.tree.Trees.Trees.findAllRuleNodes(tree, fol_simplifiedParser.RULE_formula)
        for name in names:
            funcname = name.getPayload()

if __name__=="__main__":
    m  =FindNegation()
    test_string = """Predicates:
WalksInRain(x) ::: x walks in the rain.
GetWet(x) ::: x gets wet.
ExercisesALot(x) ::: x exercises a lot.
GetFit(x) ::: x gets fit.
Premises:
∀x (WalksInRain(x) → GetWet(x)) ::: If someone walks in the rain, they will get wet.
∀y (ExercisesALot(y) → GetFit(y)) ::: If someone exercises a lot, they will get fit.
(WalksInRain(john) ∨ ¬GetFit(john)) ::: John walks in the rain or he will not get fit.
Conclusion:
GetWet(john) ∨ ¬ExercisesALot(john) ::: John will get wet or he does not exercises a lot.
"""
    m(test_string)