import antlr4

from .antlr.folListener import folListener
from .antlr.folParser import folParser

# TODO make encode using generic class
from .antlr_simplified.folListener import folListener as fol_simplifiedListener
from .antlr_simplified.folParser import folParser as fol_simplifiedParser

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

# implmented types conjecture | axiom
# not implemented types:
# hypothesis | definition| assumption| lemma | theorem | corollary | negated_conjecture | plain | type | interpretation | fi_domain | fi_functors | fi_predicates | unknown

class TPTPTranslator(folListener):
    """
    Translate FOL to TPTP
    """
    def __init__(self, ):
        self.tptp = {}

    def get_tptp(self, ctx) -> dict:
        """
        Return the TPTP representation of the given context
        """
        return self.tptp[ctx]

    def set_tptp(self, ctx, value) -> dict:
        """
        Set the TPTP representation for the given context
        """
        self.tptp[ctx] = value

    # Exit a parse tree produced by folParser#start.
    def exitStart(self, ctx:folParser.StartContext):
        buffer = self.get_tptp(ctx.premises()) + "\n" + self.get_tptp(ctx.conclusion())
        self.set_tptp(ctx, buffer)

    # # Exit a parse tree produced by folParser#predicates.
    # def exitPredicates(self, ctx:folParser.PredicatesContext):
    #     pass

    # # Exit a parse tree produced by folParser#predicate.
    # def exitPredicate(self, ctx:folParser.PredicateContext):
    #     pass

    # Exit a parse tree produced by folParser#premises.
    def exitPremises(self, ctx:folParser.PremisesContext):
        buffer = ""
        for i,axio in enumerate(ctx.formula()):
            buffer += f"fof(axio{i}, axiom,{self.get_tptp(axio)}).\n"
        self.set_tptp(ctx, buffer)

    # Exit a parse tree produced by folParser#conclusion.
    def exitConclusion(self, ctx:folParser.ConclusionContext):
        buffer = ""
        for i,conj in enumerate(ctx.formula()):
            buffer += f"fof(conj{i}, conjecture,{self.get_tptp(conj)}).\n"
        self.set_tptp(ctx, buffer)

    # Exit a parse tree produced by folParser#negation.
    def exitNegation(self, ctx:folParser.NegationContext):
        self.set_tptp(ctx, f"~{self.get_tptp(ctx.formula())}")

    # Exit a parse tree produced by folParser#binary_connective.
    def exitBinary_connective(self, ctx:folParser.Binary_connectiveContext):
        formulas = ctx.formula()
        self.set_tptp(ctx, f"{self.get_tptp(formulas[0])}{ self.get_tptp(ctx.bin_connective())}{self.get_tptp(formulas[1])}")

    # Exit a parse tree produced by folParser#quantification.
    def exitQuantification(self, ctx:folParser.QuantificationContext):
        self.set_tptp(ctx, f"{self.get_tptp(ctx.quantifier())}[{self.get_tptp(ctx.var_constant())}] :{self.get_tptp(ctx.formula())}")

    # Exit a parse tree produced by folParser#atomic_formula.
    def exitAtomic_formula(self, ctx:folParser.Atomic_formulaContext):
        buffer = f"{self.get_tptp(ctx.pred_constant())}("
        terms = ctx.term()
        buffer += f"{self.get_tptp(terms[0])}"
        for t in terms[1:]:
            buffer += f", {self.get_tptp(t)}"
        self.set_tptp(ctx, buffer + ")")

    # Exit a parse tree produced by folParser#parenthesis.
    def exitParenthesis(self, ctx:folParser.ParenthesisContext):
        self.set_tptp(ctx, f"({self.get_tptp(ctx.formula())})")

    # Exit a parse tree produced by folParser#var_value.
    def exitVar_value(self, ctx:folParser.Var_valueContext):
        self.set_tptp(ctx, self.get_tptp(ctx.var_constant()))

    # Exit a parse tree produced by folParser#ind_value.
    def exitInd_value(self, ctx:folParser.Ind_valueContext):
        self.set_tptp(ctx, self.get_tptp(ctx.ind_constant()))

    # Exit a parse tree produced by folParser#conj.
    def exitConj(self, ctx:folParser.ConjContext):
        self.set_tptp(ctx, " & ")

    # Exit a parse tree produced by folParser#disj.
    def exitDisj(self, ctx:folParser.DisjContext):
        self.set_tptp(ctx, " | ")

    # Exit a parse tree produced by folParser#impl.
    def exitImpl(self, ctx:folParser.ImplContext):
        self.set_tptp(ctx, " => ")

    # Exit a parse tree produced by folParser#bicond.
    def exitBicond(self, ctx:folParser.BicondContext):
        self.set_tptp(ctx, " <=> ")

    # Exit a parse tree produced by folParser#xor.
    def exitXor(self, ctx:folParser.XorContext):
        self.set_tptp(ctx,"<~> ")

    # Exit a parse tree produced by folParser#forall.
    def exitForall(self, ctx:folParser.ForallContext):
        self.set_tptp(ctx, "! ")

    # Exit a parse tree produced by folParser#exits.
    def exitExists(self, ctx:folParser.ExistsContext):
        self.set_tptp(ctx, "? ")

    # Exit a parse tree produced by folParser#ind_constant.
    def exitInd_constant(self, ctx:folParser.Ind_constantContext):
        self.set_tptp(ctx, ctx.IDENTIFIER().getText().lower())

    # Exit a parse tree produced by folParser#var_constant.
    def exitVar_constant(self, ctx:folParser.Var_constantContext):
        self.set_tptp(ctx, ctx.IDENTIFIER().getText().upper())

    # Exit a parse tree produced by folParser#pred_constant.
    def exitPred_constant(self, ctx:folParser.Pred_constantContext):
        self.set_tptp(ctx, ctx.IDENTIFIER().getText().lower())

class SimplifiedTPTPTranslator(fol_simplifiedListener):
    """
    Translate FOL to TPTP
    """
    def __init__(self, ):
        self.tptp = {}

    def get_tptp(self, ctx) -> dict:
        """
        Return the TPTP representation of the given context
        """
        return self.tptp[ctx]

    def set_tptp(self, ctx, value) -> dict:
        """
        Set the TPTP representation for the given context
        """
        self.tptp[ctx] = value

    # Exit a parse tree produced by folParser#start.
    def exitStart(self, ctx:fol_simplifiedParser.StartContext):
        buffer = self.get_tptp(ctx.premises()) + "\n" + self.get_tptp(ctx.conclusion())
        self.set_tptp(ctx, buffer)

    # # Exit a parse tree produced by folParser#predicates.
    # def exitPredicates(self, ctx:folParser.PredicatesContext):
    #     pass

    # # Exit a parse tree produced by folParser#predicate.
    # def exitPredicate(self, ctx:folParser.PredicateContext):
    #     pass

    # Exit a parse tree produced by folParser#premises.
    def exitPremises(self, ctx:fol_simplifiedParser.PremisesContext):
        buffer = ""
        for i,axio in enumerate(ctx.formula()):
            buffer += f"fof(axio{i}, axiom,{self.get_tptp(axio)}).\n"
        self.set_tptp(ctx, buffer)

    # Exit a parse tree produced by folParser#conclusion.
    def exitConclusion(self, ctx:fol_simplifiedParser.ConclusionContext):
        buffer = ""
        for i,conj in enumerate(ctx.formula()):
            buffer += f"fof(conj{i}, conjecture,{self.get_tptp(conj)}).\n"
        self.set_tptp(ctx, buffer)

    # Exit a parse tree produced by folParser#negation.
    def exitNegation(self, ctx:fol_simplifiedParser.NegationContext):
        self.set_tptp(ctx, f"~{self.get_tptp(ctx.formula())}")

    # Exit a parse tree produced by folParser#binary_connective.
    def exitBinary_connective(self, ctx:fol_simplifiedParser.Binary_connectiveContext):
        formulas = ctx.formula()
        self.set_tptp(ctx, f"{self.get_tptp(formulas[0])}{ self.get_tptp(ctx.bin_connective())}{self.get_tptp(formulas[1])}")

    # Exit a parse tree produced by folParser#quantification.
    def exitQuantification(self, ctx:fol_simplifiedParser.QuantificationContext):
        self.set_tptp(ctx, f"{self.get_tptp(ctx.quantifier())}[{self.get_tptp(ctx.var_constant())}] :{self.get_tptp(ctx.formula())}")

    # Exit a parse tree produced by folParser#atomic_formula.
    def exitAtomic_formula(self, ctx:fol_simplifiedParser.Atomic_formulaContext):
        buffer = f"{self.get_tptp(ctx.pred_constant())}("
        terms = ctx.term()
        buffer += f"{self.get_tptp(terms[0])}"
        for t in terms[1:]:
            buffer += f", {self.get_tptp(t)}"
        self.set_tptp(ctx, buffer + ")")

    # Exit a parse tree produced by folParser#parenthesis.
    def exitParenthesis(self, ctx:fol_simplifiedParser.ParenthesisContext):
        self.set_tptp(ctx, f"({self.get_tptp(ctx.formula())})")

    # Exit a parse tree produced by folParser#var_value.
    def exitVar_value(self, ctx:fol_simplifiedParser.Var_valueContext):
        self.set_tptp(ctx, self.get_tptp(ctx.var_constant()))

    # Exit a parse tree produced by folParser#ind_value.
    def exitInd_value(self, ctx:fol_simplifiedParser.Ind_valueContext):
        self.set_tptp(ctx, self.get_tptp(ctx.ind_constant()))

    # Exit a parse tree produced by folParser#conj.
    def exitConj(self, ctx:fol_simplifiedParser.ConjContext):
        self.set_tptp(ctx, " & ")

    # Exit a parse tree produced by folParser#disj.
    def exitDisj(self, ctx:fol_simplifiedParser.DisjContext):
        self.set_tptp(ctx, " | ")

    # Exit a parse tree produced by folParser#impl.
    def exitImpl(self, ctx:fol_simplifiedParser.ImplContext):
        self.set_tptp(ctx, " => ")

    # Exit a parse tree produced by folParser#bicond.
    def exitBicond(self, ctx:fol_simplifiedParser.BicondContext):
        self.set_tptp(ctx, " <=> ")

    # Exit a parse tree produced by folParser#xor.
    def exitXor(self, ctx:fol_simplifiedParser.XorContext):
        self.set_tptp(ctx,"<~> ")

    # Exit a parse tree produced by folParser#forall.
    def exitForall(self, ctx:fol_simplifiedParser.ForallContext):
        self.set_tptp(ctx, "! ")

    # Exit a parse tree produced by folParser#exits.
    def exitExists(self, ctx:fol_simplifiedParser.ExistsContext):
        self.set_tptp(ctx, "? ")

    # Exit a parse tree produced by folParser#ind_constant.
    def exitInd_constant(self, ctx:fol_simplifiedParser.Ind_constantContext):
        self.set_tptp(ctx, ctx.IDENTIFIER().getText().lower())

    # Exit a parse tree produced by folParser#var_constant.
    def exitVar_constant(self, ctx:fol_simplifiedParser.Var_constantContext):
        self.set_tptp(ctx, ctx.IDENTIFIER().getText().upper())

    # Exit a parse tree produced by folParser#pred_constant.
    def exitPred_constant(self, ctx:fol_simplifiedParser.Pred_constantContext):
        self.set_tptp(ctx, ctx.IDENTIFIER().getText().lower())
