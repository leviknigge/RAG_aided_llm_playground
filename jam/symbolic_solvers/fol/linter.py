"""

"""
import re

import Levenshtein

from .antlr.folListener import folListener
from .antlr.folParser import folParser

def lookup_similar(word: str, vocab: set[str], threshold) -> str|None:
    """
    Look up a similar  
    """
    for v in vocab:
        similarity = Levenshtein.ratio(word,v, score_cutoff=threshold)
        if similarity > threshold:
            return v
    return None

# implmented types conjecture | axiom
# not implemented types:
# hypothesis | definition| assumption| lemma | theorem | corollary | negated_conjecture | plain | type | interpretation | fi_domain | fi_functors | fi_predicates | unknown

class FOLLinter(folListener):

    def __init__(self):
        self.warnings = []

        self.predicate_names = set()
        self.predicate_narity = dict()
        self.variable_names = set()
        self.individual_names = set()

        self.threshold = 0.8

        self.context_predicates = set()
        self.context_individuals = set()

    def get_warnings(self,) -> dict:
        return self.warnings

    # Exit a parse tree produced by folParser#premises.
    def exitPremises(self, ctx:folParser.PremisesContext):
        self.context_predicates = self.predicate_names.copy()
        self.context_individuals = self.individual_names.copy()

        # Exit a parse tree produced by folParser#conclusion.
    def exitConclusion(self, ctx:folParser.ConclusionContext):
        diff = self.predicate_names - self.context_predicates
        if len(diff) > 0:
            diff_list = ", ".join(diff)
            self.warnings.append(f"The predicates {diff_list} are only mentioned in the query.")
        diff = self.individual_names - self.context_individuals
        if len(diff) > 0:
            diff_list = ", ".join(diff)
            self.warnings.append(f"The individuals {diff_list} are only mentioned in the query.")

    # Exit a parse tree produced by folParser#atomic_formula.
    def exitAtomic_formula(self, ctx:folParser.Atomic_formulaContext):
                # check if predicate name similarity
        predicate_name = ctx.pred_constant().IDENTIFIER().getText()
        if predicate_name not in self.predicate_names:
            # is the new name similar to existing ones?
            matched_id = lookup_similar(predicate_name, self.predicate_names, self.threshold)
            if matched_id is not None:
                self.warnings.append(f"{predicate_name} is similar to an existing predicate. Did you mean {matched_id}?")
            else:
                self.predicate_names.add(predicate_name)
                self.predicate_narity[predicate_name] = len(ctx.term())
        else:
            # check n-arity of predicate
            if self.predicate_narity[predicate_name] != len(ctx.term()):
                self.warnings.append(f"{predicate_name} is used with too many parameters.")

        # Exit a parse tree produced by folParser#ind_value.
    def exitInd_value(self, ctx:folParser.Ind_valueContext):
        individual_name = ctx.ind_constant().IDENTIFIER().getText()

        if individual_name not in self.individual_names:
            # is the new name similar to existing ones?
            matched_id = lookup_similar(individual_name, self.individual_names,self.threshold)
            if matched_id is not None:
                self.warnings.append(f"{individual_name} is similar to an existing individual. Did you mean {matched_id}?")
            else:
                self.individual_names.add(individual_name)

    # Exit a parse tree produced by folParser#var_value.
    def exitVar_value(self, ctx:folParser.Var_valueContext):
        var_name = ctx.var_constant().IDENTIFIER().getText()

        if var_name not in self.variable_names:
            # is the new name similar to existing ones?
            # TODO make sure that also individual are not named similar
            matched_id = lookup_similar(var_name, self.variable_names, self.threshold)
            if matched_id is not None:
                self.warnings.append(f"{var_name} is similar to an existing variable. Did you mean {matched_id}?")
            else:
                self.variable_names.add(var_name)

class NLTranlator(folListener):

    def __init__(self):
        self.warnings = []
        self.nl_text = dict()

    def get_nl(self, ctx)-> str:
        return self.nl_text[ctx]
    def set_nl(self, ctx, text:str)->None:
        self.nl_text[ctx] = text

    # Exit a parse tree produced by folParser#start.
    def exitStart(self, ctx:folParser.StartContext):
        buffer = {"context":self.get_nl(ctx.premises()), "query":self.get_nl(ctx.conclusion())}
        self.set_nl(ctx, buffer)

    # Exit a parse tree produced by folParser#premises.
    def exitPremises(self, ctx:folParser.PremisesContext):
        buffer = []
        for i,axio in enumerate(ctx.formula()):
            comments = ctx.parser.getInputStream().getHiddenTokensToRight(axio.stop.tokenIndex)
            if comments and len(comments) > 0:
                comments = comments[0].text.strip(":::")
                buffer.append((axio.getText(), self.get_nl(axio), comments))
            else:
                buffer.append((self.get_nl(axio),))
        self.set_nl(ctx, buffer)

    # Exit a parse tree produced by folParser#conclusion.
    def exitConclusion(self, ctx:folParser.ConclusionContext):
        buffer = []
        for i,conj in enumerate(ctx.formula()):
            comments = ctx.parser.getInputStream().getHiddenTokensToRight(conj.stop.tokenIndex)
            if comments and len(comments) > 0:# TODO fix NoneType error
                comments = comments[0].text.strip(":::")
                buffer.append((conj.getText(), self.get_nl(conj), comments))
            else:
                buffer.append((self.get_nl(conj),))
        self.set_nl(ctx, buffer)


    # Exit a parse tree produced by folParser#negation.
    def exitNegation(self, ctx:folParser.NegationContext):
        self.set_nl(ctx, f" not a {self.get_nl(ctx.formula())}")# TODO improve verbalization

    # Exit a parse tree produced by folParser#binary_connective.
    def exitBinary_connective(self, ctx:folParser.Binary_connectiveContext):
        formulas = ctx.formula()
        self.set_nl(ctx, f"{self.get_nl(formulas[0])}{ self.get_nl(ctx.bin_connective())}{self.get_nl(formulas[1])}")

    # Exit a parse tree produced by folParser#quantification.
    def exitQuantification(self, ctx:folParser.QuantificationContext):
        self.set_nl(ctx, f"{self.get_nl(ctx.quantifier())} {self.get_nl(ctx.var_constant())}, {self.get_nl(ctx.formula())}. ")

    # Exit a parse tree produced by folParser#atomic_formula.
    def exitAtomic_formula(self, ctx:folParser.Atomic_formulaContext):

        buffer = " "
        terms = ctx.term()
        buffer += f"{self.get_nl(terms[0])}"
        for t in terms[1:]:
            buffer += f", {self.get_nl(t)}"

        buffer += f" {self.get_nl(ctx.pred_constant())}"
        self.set_nl(ctx, buffer)

    # Exit a parse tree produced by folParser#parenthesis.
    def exitParenthesis(self, ctx:folParser.ParenthesisContext):
        self.set_nl(ctx, f"{self.get_nl(ctx.formula())}")

    # Exit a parse tree produced by folParser#var_value.
    def exitVar_value(self, ctx:folParser.Var_valueContext):
        self.set_nl(ctx, self.get_nl(ctx.var_constant()))

    # Exit a parse tree produced by folParser#ind_value.
    def exitInd_value(self, ctx:folParser.Ind_valueContext):
        self.set_nl(ctx, self.get_nl(ctx.ind_constant()))

    # Exit a parse tree produced by folParser#conj.
    def exitConj(self, ctx:folParser.ConjContext):
        self.set_nl(ctx, " and ")

    # Exit a parse tree produced by folParser#disj.
    def exitDisj(self, ctx:folParser.DisjContext):
        self.set_nl(ctx, " or ")

    # Exit a parse tree produced by folParser#impl.
    def exitImpl(self, ctx:folParser.ImplContext):
        self.set_nl(ctx, " implies that ")

    # Exit a parse tree produced by folParser#bicond.
    def exitBicond(self, ctx:folParser.BicondContext):
        self.set_nl(ctx, " is equivalent to ")

    # Exit a parse tree produced by folParser#xor.
    def exitXor(self, ctx:folParser.XorContext):
        self.set_nl(ctx," xor ")# TODO improve verbalization


    # Exit a parse tree produced by folParser#forall.
    def exitForall(self, ctx:folParser.ForallContext):
        self.set_nl(ctx, "For all ")

    # Exit a parse tree produced by folParser#exits.
    def exitExists(self, ctx:folParser.ExistsContext):
        self.set_nl(ctx, "There exists a ")

    # Exit a parse tree produced by folParser#ind_constant.
    def exitInd_constant(self, ctx:folParser.Ind_constantContext):
        text = ctx.IDENTIFIER().getText()
        nl_id = " ".join([x.lower() for x in re.split("(?<=.)(?=[A-Z])",text)])
        self.set_nl(ctx, nl_id)

    # Exit a parse tree produced by folParser#var_constant.
    def exitVar_constant(self, ctx:folParser.Var_constantContext):
        text =  ctx.IDENTIFIER().getText()
        nl_id = " ".join([x.lower() for x in re.split("(?<=.)(?=[A-Z])",text)])
        self.set_nl(ctx, nl_id)

    # Exit a parse tree produced by folParser#pred_constant.
    def exitPred_constant(self, ctx:folParser.Pred_constantContext):
        text = ctx.IDENTIFIER().getText()
        nl_id = " ".join([x.lower() for x in re.split("(?<=.)(?=[A-Z])",text)])
        self.set_nl(ctx, nl_id)
