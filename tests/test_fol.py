"""
Tests for FOL ANTLR module 
"""
import unittest

# from jam.symbolic_solvers.simplified_fol import tools
from jam.symbolic_solvers.fol.tools import FOLLinter
from jam.symbolic_solvers.fol.tools import TPTPParser

class TestFOLParser(unittest.TestCase):
    """
    Test of the Logic-LM FOL Parser
    """
    def setUp(self):
        self.parser =TPTPParser(convert=True,simplified=True,feedback_msg=True)

    def test_symbols(self):
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
        result = """fof(axio0, axiom,! [X] :(walksinrain(X) => getwet(X))).
fof(axio1, axiom,! [Y] :(exercisesalot(Y) => getfit(Y))).
fof(axio2, axiom,(walksinrain(john) | ~getfit(john))).

fof(conj0, conjecture,getwet(john) | ~exercisesalot(john)).
"""
        tptp_text, _ = self.parser.encode(test_string)
        self.assertEqual(tptp_text,result)

    def test_t1(self):
        test_string= """Predicates:
ConsumesWater(x) ::: x consumes a significant amount of water.
Hydrated(x) ::: x experiences a state of hydration.
ConsumesSugar(x) ::: x ingests excessive amounts of sugar.
SugarCrash(x) ::: x experiences a sugar crash.
Premises:
∀x (ConsumesWater(x) → Hydrated(x)) ::: If an individual consumes a significant amount of water, they will experience a state of hydration.
∀x (ConsumesSugar(x) → SugarCrash(x)) ::: If excessive amounts of sugar are ingested, a sugar crash will ensue.
(ConsumesWater(jane) ∨ ¬SugarCrash(jane)) ::: Jane consumes ample water or she will not experience a sugar crash.
Conclusion:
Hydrated(jane) ∨ ¬ConsumesSugar(jane) ::: Jane will feel hydrated or she doesn't eat too much sugar.
"""
        result = """fof(axio0, axiom,! [X] :(consumeswater(X) => hydrated(X))).
fof(axio1, axiom,! [X] :(consumessugar(X) => sugarcrash(X))).
fof(axio2, axiom,(consumeswater(jane) | ~sugarcrash(jane))).

fof(conj0, conjecture,hydrated(jane) | ~consumessugar(jane)).
"""
        tptp_text, _ = self.parser.encode(test_string)
        self.assertEqual(tptp_text,result)

class TestFOLLinter(unittest.TestCase):
    """
    Test of the Logic-LM FOL Parser
    """

    def setUp(self):
        self.parser =TPTPParser(convert=True,simplified=True,feedback_msg=True)
        self.linter = FOLLinter()

    def test_nl(self):
        test_string = """Predicates:
WalksInRain(x) ::: x walks in the rain.
GetWet(x) ::: x gets wet.
ExercisesALot(x) ::: x exercises a lot.
GetFit(x) ::: x gets fit.
Premises:
∀x (WalksInRain(x) → GetWet(x)) ::: If someone plays outside, they will get wet.
∀y (ExercisesALot(y) → GetFit(y)) ::: If someone exercises a lot, they will get fit.
(WalksInRain(john) ∨ ¬GetFit(john)) ::: John walks in the rain or he will not get fit.
Conclusion:
GetWet(john) ∨ ¬ExercisesALot(john) ::: John will get wet or he does not exercises a lot.
"""
        result = """fof(axio0, axiom,! [X] :(walksinrain(X) => getwet(X))).
fof(axio1, axiom,! [Y] :(exercisesalot(Y) => getfit(Y))).
fof(axio2, axiom,(walksinrain(john) | ~getfit(john))).

fof(conj0, conjecture,getwet(john) | ~exercisesalot(john)).
"""
        tptp_text, ast = self.parser.encode(test_string)
        warnings = self.linter(ast,"If someone walks in the rain, they will get wet. If someone exercises a lot, they will get fit. John walks in the rain or he will not get fit","John will get wet or he does not exercises a lot.")

        self.assertEqual(warnings,result, )

    def test_missing_nl(self):
        test_string = """Predicates:
WalksInRain(x) ::: x walks in the rain.
GetWet(x) ::: x gets wet.
ExercisesALot(x) ::: x exercises a lot.
GetFit(x) ::: x gets fit.
Premises:
∀x (WalksInRain(x) → GetWet(x)) 
∀y (ExercisesALot(y) → GetFit(y)) ::: If someone exercises a lot, they will get fit.
(WalksInRain(john) ∨ ¬GetFit(john)) ::: John walks in the rain or he will not get fit.
Conclusion:
GetWet(john) ∨ ¬ExercisesALot(john) 
"""
        result = """fof(axio0, axiom,! [X] :(walksinrain(X) => getwet(X))).
fof(axio1, axiom,! [Y] :(exercisesalot(Y) => getfit(Y))).
fof(axio2, axiom,(walksinrain(john) | ~getfit(john))).

fof(conj0, conjecture,getwet(john) | ~exercisesalot(john)).
"""
        tptp_text, ast = self.linter.encode(test_string)
        self.linter.lint_with_context(ast,"test","test1")
        self.assertEqual(tptp_text,result, )

if __name__ == '__main__':
    unittest.main()
