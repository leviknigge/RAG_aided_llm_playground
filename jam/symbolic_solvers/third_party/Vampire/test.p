fof(a1, axiom, (![X]: drinkRegularly(X, coffee) => isDependentOn(X, caffeine))).
fof(a2, axiom, ![X]: (drinkRegularly(X, coffee) | ~wantToBeAddictedTo(X, caffeine))).
fof(a3, axiom, ![X]: (~wantToBeAddictedTo(X, caffeine) => ~awareThatDrug(X, caffeine))).
fof(a4, axiom, ~student(rina) | awareThatDrug(rina, caffeine)).
fof(a5, axiom, ~isDependentOn(rina, caffeine) | ~student(rina)).
fof(c1, conjecture, ~wantToBeAddictedTo(rina, caffeine) | ~awareThatDrug(rina, caffeine)).
