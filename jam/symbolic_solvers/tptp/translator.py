#!/usr/bin/env python3
"""
TPTP Parser
"""

import antlr4

class ErrorListener(antlr4.error.ErrorListener.ErrorListener) :
    """Error Listener
    """
    def syntaxError(self, recognizer: antlr4.Recognizer, offendingSymbol, line: int, column: int, msg: str, e: antlr4.RecognitionException):
        # stack = recognizer.getRuleInvocationStack()
        # stack.reverse()
        raise antlr4.error.Errors.ParseCancellationException(msg=msg)
        # logging.debug("rule stack: ", str(stack))
        # logging.debug("line", line, ":", column, "at", offendingSymbol, ":", msg)

# extend for error stategies
# class MyErrorStrategy(antlr4.error.BailErrorStrategy):
#     def recover(self, recognizer:folParser, e:antlr4.error.Errors.RecognitionException):
#         recognizer._errHandler.reportError(recognizer,e)
#         super().recover(recognizer,e)
