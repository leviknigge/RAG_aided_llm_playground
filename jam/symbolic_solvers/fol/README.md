# TPTP parser

The parser is generated with ANTLR4. 

## Installation 
At the moment the hydra package (which is used in the whole project) requires the ANTLR 4.9.3 runtime. Due to simplisity we are using the same version for the TPTP parser and upgrade once hydra allows for a more recent version of ANTLR4. 

Remark: If we are creating a Java,C++, etc. parser the limitation of the old runtime used by hydra does not hold. 

### Requirements 
ANTLR4 uses Java (at least version 1.7) (only required to run ANTLR not for the systems running the created parser)
```
wget https://github.com/antlr/website-antlr4/blob/gh-pages/download/antlr-4.9.3-complete.jar 
sudo mv antlr-4.9.3-complete.jar /usr/share/java/antlr4/antlr-4.9.3-complete.jar
sudo chown root /usr/share/java/antlr4/antlr-4.9.3-complete.jar
```
Add jar to CLASSPATH in .bash_profile 
```
export CLASSPATH=".:/usr/share/java/antlr4/antlr-4.9.3-complete.jar:$CLASSPATH"
```
Create alias in .bashrc
```
alias antlr4='java -Xmx500M -cp "/usr/share/java/antlr4/antlr-4.9.3-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java -Xmx500M -cp "/usr/share/java/antlr4/antlr-4.9.3-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
```


## Build parser

transformes FOL into TPTP format using ANTLR


'''
antlr4 -Dlanguage=Python3 -encoding utf8 fol.g4
'''

For Java the test-rig in ANTLR can be use 
'''
javac fol*.java
''' 
possible check CLASSPATH
'''
grun fol start input.txt -gui
'''

## test input 
'''
(∀x(p(x) → q(x)) ∧ p(a))
(∀x((p(x) → q(x)) ∧ (r(x) → s(x))) ∧ (p(a) ∨ ¬s(a)))
¬q(a)
'''