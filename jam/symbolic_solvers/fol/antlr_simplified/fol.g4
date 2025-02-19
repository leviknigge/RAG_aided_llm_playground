grammar fol;
// based on Logic LM

@lexer::members {nesting = 0}

@parser::members {sym_local = []
def is_var(self,var_id):
    return var_id in self.sym_local
def add_scope(self, var_id):
    self.sym_local.append(var_id)
def remove_scope(self, ):
    self.sym_local.pop()
}

start 
    : NEWLINE* predicates NEWLINE+ premises NEWLINE+ conclusion NEWLINE* EOF;

predicates
    : PREDICATE_START NEWLINE* predicate (NEWLINE predicate)*;

predicate
    : pred_constant LPAREN term (SEP term)* RPAREN;

premises
    : PREMISES_START NEWLINE* formula (NEWLINE formula)*;

conclusion
    : CONCLUSION_START NEWLINE* formula (NEWLINE formula)*; 

formula
    : pred_constant LPAREN term (SEP term)* RPAREN  # atomic_formula
    | LPAREN formula RPAREN                         # parenthesis
    | NOT formula                                   # negation
    | formula NEWLINE* bin_connective NEWLINE* formula                # binary_connective
    | quantifier  var_constant {self.add_scope($var_constant.text)} formula {self.remove_scope()}           # quantification 
    ;

term
    : {self.is_var(self.getCurrentToken().text)}? var_constant               # var_value
    | ind_constant                                # ind_value
    ;

bin_connective
    : CONJ      # conj
    | DISJ      # disj
    | IMPL      # impl
    | BICOND    # bicond
    | XOR       # xor
    ;

quantifier
    : FORALL    # forall
    | EXISTS    # exists
    ;
ind_constant
    : IDENTIFIER
    ; 
var_constant
    : IDENTIFIER
    ;

pred_constant
    : IDENTIFIER
    ;

IDENTIFIER: (LETTER | DIGIT)+;
fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9]; 

PREDICATE_START: 'Predicates:';
PREMISES_START: 'Premises:';
CONCLUSION_START: 'Conclusion:';

SEP: ',';
QUE: '?';
UND: '_';

LPAREN: '(' {self.nesting +=1};
RPAREN: ')' {self.nesting -=1};

NOT: '¬';
CONJ: '∧';
DISJ: '∨';
IMPL: '→';
BICOND: '↔';
XOR: '⊕';

FORALL: '∀';
EXISTS: '∃';

IGNORE_NEWLINE : '\r'? '\n' {self.nesting>0}? -> skip ;
NEWLINE: '\r'? '\n';
LINE_ESCAPE : '\\' '\r'? '\n' -> skip ;
WHITESPACE: (' ' | '\t')+ -> skip ;
SL_COMMENT: ':::' ~('\r' | '\n')* -> channel(2) ;
