#!/usr/bin/env python3
"""
Logic LM Parser
"""

import dataclasses
import re
import pathlib
import tempfile
import subprocess

import nltk
import antlr4
import antlr4.error
import antlr4.error.Errors

from .. import basesolver

from . import nli
from . import linter
from . import tptp_translator

from .antlr.folParser import folParser
from .antlr.folLexer import folLexer
from .antlr_simplified.folParser import folParser as fol_simplifiedParser
from .antlr_simplified.folLexer import folLexer as fol_simplifiedLexer


@dataclasses.dataclass
class TPTPParserConfig(basesolver.ParserConfig):
    """
    TPTP Parser config
    """
    _target_: str = "jam.symbolic_solvers.fol.TPTPParser"
    convert: bool = True
    simplified: bool = True
    feedback_msg: bool = True

class TPTPParser(basesolver.Parser):
    """
    Parser from FOL to TPTP format

    """
    def __init__(self, convert: bool = True, simplified: bool = True, feedback_msg: bool = True) -> None:
        """
        convert: if the input should be convert from FOL to TPTP or the template structure be removed
        """
        super().__init__()
        # regex depends on prompt, make sure that they fit to prompt
        self.block_regex = re.compile(r"(?P<text>.+)",re.DOTALL)
        if convert:
            self.parse = self.encode
        else:
            self.parse = lambda x,y: x + '\n' + y, None

        if simplified:
            self.lexer = fol_simplifiedLexer
            self.parser = fol_simplifiedParser
            self.translator = tptp_translator.SimplifiedTPTPTranslator
        else:
            self.lexer = folLexer
            self.parser = folParser
            self.translator = tptp_translator.TPTPTranslator

        self.feedback_msg = feedback_msg


    def encode(self, input_string: str)-> tuple[str, folParser.StartContext] :
        input_stream = antlr4.InputStream(input_string)
        lexer = self.lexer(input_stream)
        stream = antlr4.CommonTokenStream(lexer)
        parser = self.parser(stream)
        # parser._errHandler = antlr4.error.ErrorStrategy.BailErrorStrategy()
        parser.removeErrorListeners()
        parser.addErrorListener(tptp_translator.ErrorListener())
        tree = parser.start()

        # traverse tree with listener + walker
        listener = self.translator()
        walker = antlr4.ParseTreeWalker()
        walker.walk(listener, tree)
        tptp_result = listener.get_tptp(tree)

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
        return {"extract": "Predicates:\n" + extracted_text}

    def __call__(self, input_dict: dict) -> dict:
        """
        parse a block prompt 
        
        """
        input_text = input_dict["extract"]
        # convert format from FOL to TPTP
        try:
            tptp_problem, parse_ast = self.parse(input_text)
        except antlr4.error.Errors.CancellationException as e:
            error_message= e.args[0]
            if self.feedback_msg:
                raise basesolver.ParseException(message=error_message) from e
            else:
                raise basesolver.ParseException(message="parsing error") from e
        except RecursionError as e:
            error_message = "Solution too long. Try to remove unnecessary parts."
            if self.feedback_msg:
                raise basesolver.ParseException(message=error_message) from e
            else:
                raise basesolver.ParseException(message="parsing error") from e

        return tptp_problem, parse_ast


@dataclasses.dataclass
class FOLLinterConfig(basesolver.LinterConfig):
    """
    config for linter 
    """
    _target_ : str = "jam.symbolic_solvers.fol.FOLLinter"

class FOLLinter(basesolver.Linter):
    """
    Linter 
    """
    def __init__(self, ) -> None:
        pass


    def __call__(self, ast, **kwargs) -> list[dict]:# TODO change any
        """
        Linting
        Run linting on input! 
        """
        # traverse tree with listener + walker
        listener = linter.FOLLinter()
        walker = antlr4.ParseTreeWalker()
        walker.walk(listener, ast)
        warnings = listener.get_warnings()

        return warnings

@dataclasses.dataclass
class FOLContextLinterConfig(FOLLinterConfig):
    """
    config for linter 
    """
    _target_ : str = "jam.symbolic_solvers.fol.FOLContextLinter"
    lf_comment_threshold: float = 0.8
    comment_context_threshold: float = 0.8

class FOLContextLinter(FOLLinter):
    """
    Linter 
    """
    def __init__(self, lf_comment_threshold: float, comment_context_threshold: float) -> None:

        self.lemmatizer = nltk.stem.WordNetLemmatizer()
        self.stopwords = set(nltk.corpus.stopwords.words('english')) | {".", ":", ",", ";", "!", "?", "=", "+", "-"}
        self.nli_model = nli.NLI(device=None, api_key_env="HUGGINGFACE_KEY")

        self.lf_comment_threshold = lf_comment_threshold
        self.comment_context_threshold = comment_context_threshold

    def _preprocess(self, input_text: str):
        """
        Tokenize, lemmatize input string
        """

        tokens = nltk.word_tokenize(input_text)
        # TODO remove punctuation

        lemmas = {self.lemmatizer.lemmatize(t) for t in tokens if t not in self.stopwords}

        return lemmas


    def __call__(self, ast, **kwargs) -> list[dict]:
        """
        Linting
        """
        context = kwargs["context"]
        query = kwargs["query"]

        listener = linter.NLTranlator()
        walker = antlr4.ParseTreeWalker()
        walker.walk(listener, ast)

        nl_pair = listener.get_nl(ast)

        warnings = super().__call__(ast=ast)

        for name, text in {"context":context, "query":query}.items():
            vocab = self._preprocess(text)
            for nl in nl_pair[name]:
                if len(nl) == 3:
                    nli_score = self.nli_model.predict(nl[2],nl[1])
                    if nli_score < self.lf_comment_threshold:
                        warnings.append(f"The logical form {nl[0]} does not fit to the comment.")

                    # check if comment vocabulary matches context / query
                    comment_vocab = self._preprocess(nl[2])
                    if len(comment_vocab) > 0: # check if it is not an empty comment
                        overlap = 1 - (len(comment_vocab - vocab) / len(comment_vocab))
                        if overlap < self.comment_context_threshold:
                            warnings.append(f"The comment '{nl[2]}' does not occur in the {name}.")

        return warnings

@dataclasses.dataclass
class ConsistentTestConfig(basesolver.TestConfig):
    """
    Testing the consistency
    """
    _target_ : str = "jam.symbolic_solvers.fol.tools.ConsistentTest"

class ConsistentTest(basesolver.Test):
    def __init__(self) -> None:
        self.solver = VampireReasoner()

    def __call__(self, program: str) -> bool:

        # change program to negated conejcture
        # TODO find better way than regex, check and make sure it is correct
        neg_program = re.sub(r"fof\(conj([0-9])+,conjecture,(.*)\)\.", r"fof(conj\g<1>,negated_conjecture,\g<2>).", program, re.DOTALL)
        response = self.solver(program)
        neg_response = self.solver(neg_program)
        if not response and not neg_response:
            # Context not enough to answer question
            return False, "Context not enough to answer question"
        return True, None

@dataclasses.dataclass
class VampireReasonerConfig(basesolver.SolverConfig):
    """
    Vampire reasoner config
    """
    _target_: str = "jam.symbolic_solvers.fol.tools.VampireReasoner"

class VampireReasoner(basesolver.Solver):
    """
    Wrapper for Vampire reasoning 
    """
    def __init__(self) -> None:
        super().__init__()
        file_dir = pathlib.Path(__file__).parent.parent.resolve()
        self.vampire_exe = file_dir / "third_party/Vampire/bin/vampire_z3_rel_static_casc2023_6749.exe"

    def __call__(self, tptp_formula: str) -> bool:
        """
        param: input_obj 
        """
        with tempfile.TemporaryDirectory() as tmp_dir:
            # write to remporary file
            tptp_file = tmp_dir + "/problem.p"
            with open(tptp_file, "w", encoding="utf-8") as outfile:
                outfile.write(tptp_formula)

            # run vampire on temporary file
            try:
                # result = subprocess.run([self.vampire_exe, "--input_syntax", "tptp", "--output_mode", "smtcomp", tptp_file ], check=False, capture_output=True, shell=True)
                result = subprocess.run([self.vampire_exe, "--input_syntax", "tptp", "--output_mode", "smtcomp", tptp_file ], check=True, capture_output=True, shell=True)
            except subprocess.CalledProcessError as e:
                raise basesolver.SolverRuntimeException from e

        # read result
        # vampire looks for refutation -> unsat of the negated conjecture
        return result.stdout == b"unsat\n"
