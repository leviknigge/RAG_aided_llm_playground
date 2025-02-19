# Generated from tptp.g4 by ANTLR 4.9.3
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .tptpParser import tptpParser
else:
    from tptpParser import tptpParser

# This class defines a complete listener for a parse tree produced by tptpParser.
class tptpListener(ParseTreeListener):

    # Enter a parse tree produced by tptpParser#formular_role.
    def enterFormular_role(self, ctx:tptpParser.Formular_roleContext):
        pass

    # Exit a parse tree produced by tptpParser#formular_role.
    def exitFormular_role(self, ctx:tptpParser.Formular_roleContext):
        pass


    # Enter a parse tree produced by tptpParser#start.
    def enterStart(self, ctx:tptpParser.StartContext):
        pass

    # Exit a parse tree produced by tptpParser#start.
    def exitStart(self, ctx:tptpParser.StartContext):
        pass


    # Enter a parse tree produced by tptpParser#annotated_formula.
    def enterAnnotated_formula(self, ctx:tptpParser.Annotated_formulaContext):
        pass

    # Exit a parse tree produced by tptpParser#annotated_formula.
    def exitAnnotated_formula(self, ctx:tptpParser.Annotated_formulaContext):
        pass


    # Enter a parse tree produced by tptpParser#fof_annotated.
    def enterFof_annotated(self, ctx:tptpParser.Fof_annotatedContext):
        pass

    # Exit a parse tree produced by tptpParser#fof_annotated.
    def exitFof_annotated(self, ctx:tptpParser.Fof_annotatedContext):
        pass


    # Enter a parse tree produced by tptpParser#negation.
    def enterNegation(self, ctx:tptpParser.NegationContext):
        pass

    # Exit a parse tree produced by tptpParser#negation.
    def exitNegation(self, ctx:tptpParser.NegationContext):
        pass


    # Enter a parse tree produced by tptpParser#binary_connective.
    def enterBinary_connective(self, ctx:tptpParser.Binary_connectiveContext):
        pass

    # Exit a parse tree produced by tptpParser#binary_connective.
    def exitBinary_connective(self, ctx:tptpParser.Binary_connectiveContext):
        pass


    # Enter a parse tree produced by tptpParser#quantification.
    def enterQuantification(self, ctx:tptpParser.QuantificationContext):
        pass

    # Exit a parse tree produced by tptpParser#quantification.
    def exitQuantification(self, ctx:tptpParser.QuantificationContext):
        pass


    # Enter a parse tree produced by tptpParser#atomic_formula.
    def enterAtomic_formula(self, ctx:tptpParser.Atomic_formulaContext):
        pass

    # Exit a parse tree produced by tptpParser#atomic_formula.
    def exitAtomic_formula(self, ctx:tptpParser.Atomic_formulaContext):
        pass


    # Enter a parse tree produced by tptpParser#parenthesis.
    def enterParenthesis(self, ctx:tptpParser.ParenthesisContext):
        pass

    # Exit a parse tree produced by tptpParser#parenthesis.
    def exitParenthesis(self, ctx:tptpParser.ParenthesisContext):
        pass


    # Enter a parse tree produced by tptpParser#ind_value.
    def enterInd_value(self, ctx:tptpParser.Ind_valueContext):
        pass

    # Exit a parse tree produced by tptpParser#ind_value.
    def exitInd_value(self, ctx:tptpParser.Ind_valueContext):
        pass


    # Enter a parse tree produced by tptpParser#var_value.
    def enterVar_value(self, ctx:tptpParser.Var_valueContext):
        pass

    # Exit a parse tree produced by tptpParser#var_value.
    def exitVar_value(self, ctx:tptpParser.Var_valueContext):
        pass


    # Enter a parse tree produced by tptpParser#ind_constant.
    def enterInd_constant(self, ctx:tptpParser.Ind_constantContext):
        pass

    # Exit a parse tree produced by tptpParser#ind_constant.
    def exitInd_constant(self, ctx:tptpParser.Ind_constantContext):
        pass


    # Enter a parse tree produced by tptpParser#var_constant.
    def enterVar_constant(self, ctx:tptpParser.Var_constantContext):
        pass

    # Exit a parse tree produced by tptpParser#var_constant.
    def exitVar_constant(self, ctx:tptpParser.Var_constantContext):
        pass


    # Enter a parse tree produced by tptpParser#pred_constant.
    def enterPred_constant(self, ctx:tptpParser.Pred_constantContext):
        pass

    # Exit a parse tree produced by tptpParser#pred_constant.
    def exitPred_constant(self, ctx:tptpParser.Pred_constantContext):
        pass


    # Enter a parse tree produced by tptpParser#name.
    def enterName(self, ctx:tptpParser.NameContext):
        pass

    # Exit a parse tree produced by tptpParser#name.
    def exitName(self, ctx:tptpParser.NameContext):
        pass


    # Enter a parse tree produced by tptpParser#conj.
    def enterConj(self, ctx:tptpParser.ConjContext):
        pass

    # Exit a parse tree produced by tptpParser#conj.
    def exitConj(self, ctx:tptpParser.ConjContext):
        pass


    # Enter a parse tree produced by tptpParser#disj.
    def enterDisj(self, ctx:tptpParser.DisjContext):
        pass

    # Exit a parse tree produced by tptpParser#disj.
    def exitDisj(self, ctx:tptpParser.DisjContext):
        pass


    # Enter a parse tree produced by tptpParser#impl.
    def enterImpl(self, ctx:tptpParser.ImplContext):
        pass

    # Exit a parse tree produced by tptpParser#impl.
    def exitImpl(self, ctx:tptpParser.ImplContext):
        pass


    # Enter a parse tree produced by tptpParser#bicond.
    def enterBicond(self, ctx:tptpParser.BicondContext):
        pass

    # Exit a parse tree produced by tptpParser#bicond.
    def exitBicond(self, ctx:tptpParser.BicondContext):
        pass


    # Enter a parse tree produced by tptpParser#xor.
    def enterXor(self, ctx:tptpParser.XorContext):
        pass

    # Exit a parse tree produced by tptpParser#xor.
    def exitXor(self, ctx:tptpParser.XorContext):
        pass


    # Enter a parse tree produced by tptpParser#forall.
    def enterForall(self, ctx:tptpParser.ForallContext):
        pass

    # Exit a parse tree produced by tptpParser#forall.
    def exitForall(self, ctx:tptpParser.ForallContext):
        pass


    # Enter a parse tree produced by tptpParser#exists.
    def enterExists(self, ctx:tptpParser.ExistsContext):
        pass

    # Exit a parse tree produced by tptpParser#exists.
    def exitExists(self, ctx:tptpParser.ExistsContext):
        pass



del tptpParser