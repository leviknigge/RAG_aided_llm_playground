grammar tptp;

@lexer::members {nesting = 0}

IGNORE_NEWLINE : '\r'? '\n' {self.nesting>0}? -> skip ;
NEWLINE: '\r'? '\n';
LINE_ESCAPE : '\\' '\r'? '\n' -> skip ;
WHITESPACE: (' ' | '\t')+ -> skip ;
SL_COMMENT: '%' ~('\r' | '\n')* -> channel(2) ;
BL_COMMENT: '/*' .*? '*/' -> channel(2);

FOF_ID: 'fof';
SEP: ',';
DOT: '.';

NOT: '~';
CONJ: '&';
DISJ: '|';
IMPL : '=>';
BICOND : '<=>';
XOR: '<~>';

FORALL: '!';
EXISTS: '?'; 

UPPER_ALPHA: [A-Z];
LOWER_ALPHA: [a-z];
DIGIT: [0-9];
ALPHA_NUMERIC: (UPPER_ALPHA | LOWER_ALPHA | DIGIT);

UPPER_WORD : UPPER_ALPHA ALPHA_NUMERIC*;
LOWER_WORD : LOWER_ALPHA ALPHA_NUMERIC*;


LPAREN: '(' {self.nesting +=1};
RPAREN: ')' {self.nesting -=1};

LSQPAREN: '[';
RSQPAREN: ']';
COLON: ':';

formular_role
    : 'axiom'
    | 'conjecture'
    ;

start
    : NEWLINE* annotated_formula (NEWLINE+ annotated_formula)* NEWLINE* EOF;

annotated_formula
    : fof_annotated
    ;

fof_annotated
    : FOF_ID LPAREN name SEP formular_role SEP fof_formula RPAREN DOT
    ;

fof_formula
    : pred_constant LPAREN term (SEP term)* RPAREN  # atomic_formula
    | LPAREN fof_formula RPAREN                         # parenthesis
    | NOT fof_formula                                   # negation
    | fof_formula NEWLINE* bin_connective NEWLINE* fof_formula                # binary_connective
    | quantifier LSQPAREN var_constant ( SEP var_constant)* RSQPAREN COLON NEWLINE* fof_formula               # quantification
    ;

term
    : ind_constant                                 # ind_value
    | var_constant                                 # var_value
    ;

ind_constant
    : LOWER_WORD
    ; 
var_constant
    : UPPER_WORD
    ;

pred_constant
    : LOWER_WORD
    ;

name 
    : LOWER_WORD 
    | (DIGIT)+
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

