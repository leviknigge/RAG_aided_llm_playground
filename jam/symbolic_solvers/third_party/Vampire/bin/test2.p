fof(usa, axiom, country(usa)).
fof(capital, axiom, ! [C] :country(C) => town(C)).
fof(c, conjecture, ~town(usa)).
