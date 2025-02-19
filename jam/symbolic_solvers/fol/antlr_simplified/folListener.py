# Generated from fol.g4 by ANTLR 4.9.3
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .folParser import folParser
else:
    from folParser import folParser

# This class defines a complete listener for a parse tree produced by folParser.
class folListener(ParseTreeListener):

    # Enter a parse tree produced by folParser#start.
    def enterStart(self, ctx:folParser.StartContext):
        pass

    # Exit a parse tree produced by folParser#start.
    def exitStart(self, ctx:folParser.StartContext):
        pass


    # Enter a parse tree produced by folParser#predicates.
    def enterPredicates(self, ctx:folParser.PredicatesContext):
        pass

    # Exit a parse tree produced by folParser#predicates.
    def exitPredicates(self, ctx:folParser.PredicatesContext):
        pass


    # Enter a parse tree produced by folParser#predicate.
    def enterPredicate(self, ctx:folParser.PredicateContext):
        pass

    # Exit a parse tree produced by folParser#predicate.
    def exitPredicate(self, ctx:folParser.PredicateContext):
        pass


    # Enter a parse tree produced by folParser#premises.
    def enterPremises(self, ctx:folParser.PremisesContext):
        pass

    # Exit a parse tree produced by folParser#premises.
    def exitPremises(self, ctx:folParser.PremisesContext):
        pass


    # Enter a parse tree produced by folParser#conclusion.
    def enterConclusion(self, ctx:folParser.ConclusionContext):
        pass

    # Exit a parse tree produced by folParser#conclusion.
    def exitConclusion(self, ctx:folParser.ConclusionContext):
        pass


    # Enter a parse tree produced by folParser#negation.
    def enterNegation(self, ctx:folParser.NegationContext):
        pass

    # Exit a parse tree produced by folParser#negation.
    def exitNegation(self, ctx:folParser.NegationContext):
        pass


    # Enter a parse tree produced by folParser#binary_connective.
    def enterBinary_connective(self, ctx:folParser.Binary_connectiveContext):
        pass

    # Exit a parse tree produced by folParser#binary_connective.
    def exitBinary_connective(self, ctx:folParser.Binary_connectiveContext):
        pass


    # Enter a parse tree produced by folParser#quantification.
    def enterQuantification(self, ctx:folParser.QuantificationContext):
        pass

    # Exit a parse tree produced by folParser#quantification.
    def exitQuantification(self, ctx:folParser.QuantificationContext):
        pass


    # Enter a parse tree produced by folParser#atomic_formula.
    def enterAtomic_formula(self, ctx:folParser.Atomic_formulaContext):
        pass

    # Exit a parse tree produced by folParser#atomic_formula.
    def exitAtomic_formula(self, ctx:folParser.Atomic_formulaContext):
        pass


    # Enter a parse tree produced by folParser#parenthesis.
    def enterParenthesis(self, ctx:folParser.ParenthesisContext):
        pass

    # Exit a parse tree produced by folParser#parenthesis.
    def exitParenthesis(self, ctx:folParser.ParenthesisContext):
        pass


    # Enter a parse tree produced by folParser#var_value.
    def enterVar_value(self, ctx:folParser.Var_valueContext):
        pass

    # Exit a parse tree produced by folParser#var_value.
    def exitVar_value(self, ctx:folParser.Var_valueContext):
        pass


    # Enter a parse tree produced by folParser#ind_value.
    def enterInd_value(self, ctx:folParser.Ind_valueContext):
        pass

    # Exit a parse tree produced by folParser#ind_value.
    def exitInd_value(self, ctx:folParser.Ind_valueContext):
        pass


    # Enter a parse tree produced by folParser#conj.
    def enterConj(self, ctx:folParser.ConjContext):
        pass

    # Exit a parse tree produced by folParser#conj.
    def exitConj(self, ctx:folParser.ConjContext):
        pass


    # Enter a parse tree produced by folParser#disj.
    def enterDisj(self, ctx:folParser.DisjContext):
        pass

    # Exit a parse tree produced by folParser#disj.
    def exitDisj(self, ctx:folParser.DisjContext):
        pass


    # Enter a parse tree produced by folParser#impl.
    def enterImpl(self, ctx:folParser.ImplContext):
        pass

    # Exit a parse tree produced by folParser#impl.
    def exitImpl(self, ctx:folParser.ImplContext):
        pass


    # Enter a parse tree produced by folParser#bicond.
    def enterBicond(self, ctx:folParser.BicondContext):
        pass

    # Exit a parse tree produced by folParser#bicond.
    def exitBicond(self, ctx:folParser.BicondContext):
        pass


    # Enter a parse tree produced by folParser#xor.
    def enterXor(self, ctx:folParser.XorContext):
        pass

    # Exit a parse tree produced by folParser#xor.
    def exitXor(self, ctx:folParser.XorContext):
        pass


    # Enter a parse tree produced by folParser#forall.
    def enterForall(self, ctx:folParser.ForallContext):
        pass

    # Exit a parse tree produced by folParser#forall.
    def exitForall(self, ctx:folParser.ForallContext):
        pass


    # Enter a parse tree produced by folParser#exists.
    def enterExists(self, ctx:folParser.ExistsContext):
        pass

    # Exit a parse tree produced by folParser#exists.
    def exitExists(self, ctx:folParser.ExistsContext):
        pass


    # Enter a parse tree produced by folParser#ind_constant.
    def enterInd_constant(self, ctx:folParser.Ind_constantContext):
        pass

    # Exit a parse tree produced by folParser#ind_constant.
    def exitInd_constant(self, ctx:folParser.Ind_constantContext):
        pass


    # Enter a parse tree produced by folParser#var_constant.
    def enterVar_constant(self, ctx:folParser.Var_constantContext):
        pass

    # Exit a parse tree produced by folParser#var_constant.
    def exitVar_constant(self, ctx:folParser.Var_constantContext):
        pass


    # Enter a parse tree produced by folParser#pred_constant.
    def enterPred_constant(self, ctx:folParser.Pred_constantContext):
        pass

    # Exit a parse tree produced by folParser#pred_constant.
    def exitPred_constant(self, ctx:folParser.Pred_constantContext):
        pass



del folParser