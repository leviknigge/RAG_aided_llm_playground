// Generated from /home/fhoppe/Workspace/augmented_llm_playground/jam/symbolic_solvers/fol/antlr_simplified/fol.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class folParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IDENTIFIER=1, PREDICATE_START=2, PREMISES_START=3, CONCLUSION_START=4, 
		SEP=5, QUE=6, UND=7, LPAREN=8, RPAREN=9, NOT=10, CONJ=11, DISJ=12, IMPL=13, 
		BICOND=14, XOR=15, FORALL=16, EXISTS=17, IGNORE_NEWLINE=18, NEWLINE=19, 
		LINE_ESCAPE=20, WHITESPACE=21, SL_COMMENT=22;
	public static final int
		RULE_start = 0, RULE_predicates = 1, RULE_predicate = 2, RULE_premises = 3, 
		RULE_conclusion = 4, RULE_formula = 5, RULE_term = 6, RULE_bin_connective = 7, 
		RULE_quantifier = 8, RULE_ind_constant = 9, RULE_var_constant = 10, RULE_pred_constant = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "predicates", "predicate", "premises", "conclusion", "formula", 
			"term", "bin_connective", "quantifier", "ind_constant", "var_constant", 
			"pred_constant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'Predicates:'", "'Premises:'", "'Conclusion:'", "','", "'?'", 
			"'_'", "'('", "')'", "'\\u00AC'", "'\\u2227'", "'\\u2228'", "'\\u2192'", 
			"'\\u2194'", "'\\u2295'", "'\\u2200'", "'\\u2203'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IDENTIFIER", "PREDICATE_START", "PREMISES_START", "CONCLUSION_START", 
			"SEP", "QUE", "UND", "LPAREN", "RPAREN", "NOT", "CONJ", "DISJ", "IMPL", 
			"BICOND", "XOR", "FORALL", "EXISTS", "IGNORE_NEWLINE", "NEWLINE", "LINE_ESCAPE", 
			"WHITESPACE", "SL_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "fol.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	sym_local = []
	def is_var(self,var_id):
	    return var_id in self.sym_local
	def add_scope(self, var_id):
	    self.sym_local.append(var_id)
	def remove_scope(self, ):
	    self.sym_local.pop()

	public folParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public PredicatesContext predicates() {
			return getRuleContext(PredicatesContext.class,0);
		}
		public PremisesContext premises() {
			return getRuleContext(PremisesContext.class,0);
		}
		public ConclusionContext conclusion() {
			return getRuleContext(ConclusionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(folParser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(folParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(folParser.NEWLINE, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(24);
				match(NEWLINE);
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(30);
			predicates();
			setState(32); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(31);
				match(NEWLINE);
				}
				}
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(36);
			premises();
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				match(NEWLINE);
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(42);
			conclusion();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(43);
				match(NEWLINE);
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicatesContext extends ParserRuleContext {
		public TerminalNode PREDICATE_START() { return getToken(folParser.PREDICATE_START, 0); }
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(folParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(folParser.NEWLINE, i);
		}
		public PredicatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicates; }
	}

	public final PredicatesContext predicates() throws RecognitionException {
		PredicatesContext _localctx = new PredicatesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_predicates);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(PREDICATE_START);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(52);
				match(NEWLINE);
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(58);
			predicate();
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(59);
					match(NEWLINE);
					setState(60);
					predicate();
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateContext extends ParserRuleContext {
		public Pred_constantContext pred_constant() {
			return getRuleContext(Pred_constantContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(folParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(folParser.RPAREN, 0); }
		public List<TerminalNode> SEP() { return getTokens(folParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(folParser.SEP, i);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			pred_constant();
			setState(67);
			match(LPAREN);
			setState(68);
			term();
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(69);
				match(SEP);
				setState(70);
				term();
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PremisesContext extends ParserRuleContext {
		public TerminalNode PREMISES_START() { return getToken(folParser.PREMISES_START, 0); }
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(folParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(folParser.NEWLINE, i);
		}
		public PremisesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_premises; }
	}

	public final PremisesContext premises() throws RecognitionException {
		PremisesContext _localctx = new PremisesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_premises);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(PREMISES_START);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(79);
				match(NEWLINE);
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(85);
			formula(0);
			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(86);
					match(NEWLINE);
					setState(87);
					formula(0);
					}
					} 
				}
				setState(92);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConclusionContext extends ParserRuleContext {
		public TerminalNode CONCLUSION_START() { return getToken(folParser.CONCLUSION_START, 0); }
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(folParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(folParser.NEWLINE, i);
		}
		public ConclusionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conclusion; }
	}

	public final ConclusionContext conclusion() throws RecognitionException {
		ConclusionContext _localctx = new ConclusionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_conclusion);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(CONCLUSION_START);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(94);
				match(NEWLINE);
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			formula(0);
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(101);
					match(NEWLINE);
					setState(102);
					formula(0);
					}
					} 
				}
				setState(107);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormulaContext extends ParserRuleContext {
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
	 
		public FormulaContext() { }
		public void copyFrom(FormulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegationContext extends FormulaContext {
		public TerminalNode NOT() { return getToken(folParser.NOT, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public NegationContext(FormulaContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Binary_connectiveContext extends FormulaContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public Bin_connectiveContext bin_connective() {
			return getRuleContext(Bin_connectiveContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(folParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(folParser.NEWLINE, i);
		}
		public Binary_connectiveContext(FormulaContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class QuantificationContext extends FormulaContext {
		public Var_constantContext var_constant;
		public QuantifierContext quantifier() {
			return getRuleContext(QuantifierContext.class,0);
		}
		public Var_constantContext var_constant() {
			return getRuleContext(Var_constantContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public QuantificationContext(FormulaContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Atomic_formulaContext extends FormulaContext {
		public Pred_constantContext pred_constant() {
			return getRuleContext(Pred_constantContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(folParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(folParser.RPAREN, 0); }
		public List<TerminalNode> SEP() { return getTokens(folParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(folParser.SEP, i);
		}
		public Atomic_formulaContext(FormulaContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesisContext extends FormulaContext {
		public TerminalNode LPAREN() { return getToken(folParser.LPAREN, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(folParser.RPAREN, 0); }
		public ParenthesisContext(FormulaContext ctx) { copyFrom(ctx); }
	}

	public final FormulaContext formula() throws RecognitionException {
		return formula(0);
	}

	private FormulaContext formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
		FormulaContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_formula, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				_localctx = new Atomic_formulaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(109);
				pred_constant();
				setState(110);
				match(LPAREN);
				setState(111);
				term();
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(112);
					match(SEP);
					setState(113);
					term();
					}
					}
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(119);
				match(RPAREN);
				}
				break;
			case LPAREN:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(LPAREN);
				setState(122);
				formula(0);
				setState(123);
				match(RPAREN);
				}
				break;
			case NOT:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				match(NOT);
				setState(126);
				formula(3);
				}
				break;
			case FORALL:
			case EXISTS:
				{
				_localctx = new QuantificationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				quantifier();
				setState(128);
				((QuantificationContext)_localctx).var_constant = var_constant();
				self.add_scope((((QuantificationContext)_localctx).var_constant!=null?_input.getText(((QuantificationContext)_localctx).var_constant.start,((QuantificationContext)_localctx).var_constant.stop):null))
				setState(130);
				formula(1);
				self.remove_scope()
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(153);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Binary_connectiveContext(new FormulaContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_formula);
					setState(135);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(136);
						match(NEWLINE);
						}
						}
						setState(141);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(142);
					bin_connective();
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(143);
						match(NEWLINE);
						}
						}
						setState(148);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(149);
					formula(3);
					}
					} 
				}
				setState(155);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	 
		public TermContext() { }
		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Var_valueContext extends TermContext {
		public Var_constantContext var_constant() {
			return getRuleContext(Var_constantContext.class,0);
		}
		public Var_valueContext(TermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Ind_valueContext extends TermContext {
		public Ind_constantContext ind_constant() {
			return getRuleContext(Ind_constantContext.class,0);
		}
		public Ind_valueContext(TermContext ctx) { copyFrom(ctx); }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_term);
		try {
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new Var_valueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				if (!(self.is_var(self.getCurrentToken().text))) throw new FailedPredicateException(this, "self.is_var(self.getCurrentToken().text)");
				setState(157);
				var_constant();
				}
				break;
			case 2:
				_localctx = new Ind_valueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				ind_constant();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Bin_connectiveContext extends ParserRuleContext {
		public Bin_connectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bin_connective; }
	 
		public Bin_connectiveContext() { }
		public void copyFrom(Bin_connectiveContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImplContext extends Bin_connectiveContext {
		public TerminalNode IMPL() { return getToken(folParser.IMPL, 0); }
		public ImplContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DisjContext extends Bin_connectiveContext {
		public TerminalNode DISJ() { return getToken(folParser.DISJ, 0); }
		public DisjContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConjContext extends Bin_connectiveContext {
		public TerminalNode CONJ() { return getToken(folParser.CONJ, 0); }
		public ConjContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BicondContext extends Bin_connectiveContext {
		public TerminalNode BICOND() { return getToken(folParser.BICOND, 0); }
		public BicondContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XorContext extends Bin_connectiveContext {
		public TerminalNode XOR() { return getToken(folParser.XOR, 0); }
		public XorContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}

	public final Bin_connectiveContext bin_connective() throws RecognitionException {
		Bin_connectiveContext _localctx = new Bin_connectiveContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bin_connective);
		try {
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONJ:
				_localctx = new ConjContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				match(CONJ);
				}
				break;
			case DISJ:
				_localctx = new DisjContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				match(DISJ);
				}
				break;
			case IMPL:
				_localctx = new ImplContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(163);
				match(IMPL);
				}
				break;
			case BICOND:
				_localctx = new BicondContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(164);
				match(BICOND);
				}
				break;
			case XOR:
				_localctx = new XorContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(165);
				match(XOR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuantifierContext extends ParserRuleContext {
		public QuantifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier; }
	 
		public QuantifierContext() { }
		public void copyFrom(QuantifierContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForallContext extends QuantifierContext {
		public TerminalNode FORALL() { return getToken(folParser.FORALL, 0); }
		public ForallContext(QuantifierContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExistsContext extends QuantifierContext {
		public TerminalNode EXISTS() { return getToken(folParser.EXISTS, 0); }
		public ExistsContext(QuantifierContext ctx) { copyFrom(ctx); }
	}

	public final QuantifierContext quantifier() throws RecognitionException {
		QuantifierContext _localctx = new QuantifierContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_quantifier);
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FORALL:
				_localctx = new ForallContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(FORALL);
				}
				break;
			case EXISTS:
				_localctx = new ExistsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				match(EXISTS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Ind_constantContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(folParser.IDENTIFIER, 0); }
		public Ind_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ind_constant; }
	}

	public final Ind_constantContext ind_constant() throws RecognitionException {
		Ind_constantContext _localctx = new Ind_constantContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ind_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_constantContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(folParser.IDENTIFIER, 0); }
		public Var_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_constant; }
	}

	public final Var_constantContext var_constant() throws RecognitionException {
		Var_constantContext _localctx = new Var_constantContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_var_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Pred_constantContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(folParser.IDENTIFIER, 0); }
		public Pred_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pred_constant; }
	}

	public final Pred_constantContext pred_constant() throws RecognitionException {
		Pred_constantContext _localctx = new Pred_constantContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pred_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return formula_sempred((FormulaContext)_localctx, predIndex);
		case 6:
			return term_sempred((TermContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return self.is_var(self.getCurrentToken().text);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0016\u00b3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0001\u0000\u0005\u0000\u001a\b\u0000\n\u0000\f\u0000\u001d\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0004\u0000!\b\u0000\u000b\u0000\f\u0000\"\u0001\u0000"+
		"\u0001\u0000\u0004\u0000\'\b\u0000\u000b\u0000\f\u0000(\u0001\u0000\u0001"+
		"\u0000\u0005\u0000-\b\u0000\n\u0000\f\u00000\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0005\u00016\b\u0001\n\u0001\f\u00019\t"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001>\b\u0001\n\u0001"+
		"\f\u0001A\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002H\b\u0002\n\u0002\f\u0002K\t\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0005\u0003Q\b\u0003\n\u0003\f\u0003T\t"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003Y\b\u0003\n\u0003"+
		"\f\u0003\\\t\u0003\u0001\u0004\u0001\u0004\u0005\u0004`\b\u0004\n\u0004"+
		"\f\u0004c\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004h\b\u0004"+
		"\n\u0004\f\u0004k\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005s\b\u0005\n\u0005\f\u0005v\t\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005\u0086\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005\u008a\b\u0005\n\u0005\f\u0005\u008d\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005\u0091\b\u0005\n\u0005\f\u0005\u0094\t\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u0098\b\u0005\n\u0005\f\u0005\u009b\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00a0\b\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a7\b\u0007\u0001"+
		"\b\u0001\b\u0003\b\u00ab\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0000\u0001\n\f\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0010\u0012\u0014\u0016\u0000\u0000\u00be\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u00023\u0001\u0000\u0000\u0000\u0004B\u0001\u0000\u0000\u0000"+
		"\u0006N\u0001\u0000\u0000\u0000\b]\u0001\u0000\u0000\u0000\n\u0085\u0001"+
		"\u0000\u0000\u0000\f\u009f\u0001\u0000\u0000\u0000\u000e\u00a6\u0001\u0000"+
		"\u0000\u0000\u0010\u00aa\u0001\u0000\u0000\u0000\u0012\u00ac\u0001\u0000"+
		"\u0000\u0000\u0014\u00ae\u0001\u0000\u0000\u0000\u0016\u00b0\u0001\u0000"+
		"\u0000\u0000\u0018\u001a\u0005\u0013\u0000\u0000\u0019\u0018\u0001\u0000"+
		"\u0000\u0000\u001a\u001d\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000"+
		"\u0000\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c\u001e\u0001\u0000"+
		"\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001e \u0003\u0002\u0001"+
		"\u0000\u001f!\u0005\u0013\u0000\u0000 \u001f\u0001\u0000\u0000\u0000!"+
		"\"\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000"+
		"\u0000#$\u0001\u0000\u0000\u0000$&\u0003\u0006\u0003\u0000%\'\u0005\u0013"+
		"\u0000\u0000&%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(&\u0001"+
		"\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000"+
		"*.\u0003\b\u0004\u0000+-\u0005\u0013\u0000\u0000,+\u0001\u0000\u0000\u0000"+
		"-0\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000./\u0001\u0000\u0000"+
		"\u0000/1\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u000012\u0005\u0000"+
		"\u0000\u00012\u0001\u0001\u0000\u0000\u000037\u0005\u0002\u0000\u0000"+
		"46\u0005\u0013\u0000\u000054\u0001\u0000\u0000\u000069\u0001\u0000\u0000"+
		"\u000075\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u00008:\u0001\u0000"+
		"\u0000\u000097\u0001\u0000\u0000\u0000:?\u0003\u0004\u0002\u0000;<\u0005"+
		"\u0013\u0000\u0000<>\u0003\u0004\u0002\u0000=;\u0001\u0000\u0000\u0000"+
		">A\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000"+
		"\u0000@\u0003\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BC\u0003"+
		"\u0016\u000b\u0000CD\u0005\b\u0000\u0000DI\u0003\f\u0006\u0000EF\u0005"+
		"\u0005\u0000\u0000FH\u0003\f\u0006\u0000GE\u0001\u0000\u0000\u0000HK\u0001"+
		"\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000"+
		"JL\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000LM\u0005\t\u0000\u0000"+
		"M\u0005\u0001\u0000\u0000\u0000NR\u0005\u0003\u0000\u0000OQ\u0005\u0013"+
		"\u0000\u0000PO\u0001\u0000\u0000\u0000QT\u0001\u0000\u0000\u0000RP\u0001"+
		"\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0001\u0000\u0000\u0000"+
		"TR\u0001\u0000\u0000\u0000UZ\u0003\n\u0005\u0000VW\u0005\u0013\u0000\u0000"+
		"WY\u0003\n\u0005\u0000XV\u0001\u0000\u0000\u0000Y\\\u0001\u0000\u0000"+
		"\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\u0007\u0001"+
		"\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]a\u0005\u0004\u0000\u0000"+
		"^`\u0005\u0013\u0000\u0000_^\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000"+
		"\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bd\u0001\u0000"+
		"\u0000\u0000ca\u0001\u0000\u0000\u0000di\u0003\n\u0005\u0000ef\u0005\u0013"+
		"\u0000\u0000fh\u0003\n\u0005\u0000ge\u0001\u0000\u0000\u0000hk\u0001\u0000"+
		"\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000j\t\u0001"+
		"\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000lm\u0006\u0005\uffff\uffff"+
		"\u0000mn\u0003\u0016\u000b\u0000no\u0005\b\u0000\u0000ot\u0003\f\u0006"+
		"\u0000pq\u0005\u0005\u0000\u0000qs\u0003\f\u0006\u0000rp\u0001\u0000\u0000"+
		"\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000uw\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000wx\u0005"+
		"\t\u0000\u0000x\u0086\u0001\u0000\u0000\u0000yz\u0005\b\u0000\u0000z{"+
		"\u0003\n\u0005\u0000{|\u0005\t\u0000\u0000|\u0086\u0001\u0000\u0000\u0000"+
		"}~\u0005\n\u0000\u0000~\u0086\u0003\n\u0005\u0003\u007f\u0080\u0003\u0010"+
		"\b\u0000\u0080\u0081\u0003\u0014\n\u0000\u0081\u0082\u0006\u0005\uffff"+
		"\uffff\u0000\u0082\u0083\u0003\n\u0005\u0001\u0083\u0084\u0006\u0005\uffff"+
		"\uffff\u0000\u0084\u0086\u0001\u0000\u0000\u0000\u0085l\u0001\u0000\u0000"+
		"\u0000\u0085y\u0001\u0000\u0000\u0000\u0085}\u0001\u0000\u0000\u0000\u0085"+
		"\u007f\u0001\u0000\u0000\u0000\u0086\u0099\u0001\u0000\u0000\u0000\u0087"+
		"\u008b\n\u0002\u0000\u0000\u0088\u008a\u0005\u0013\u0000\u0000\u0089\u0088"+
		"\u0001\u0000\u0000\u0000\u008a\u008d\u0001\u0000\u0000\u0000\u008b\u0089"+
		"\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008e"+
		"\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u0092"+
		"\u0003\u000e\u0007\u0000\u008f\u0091\u0005\u0013\u0000\u0000\u0090\u008f"+
		"\u0001\u0000\u0000\u0000\u0091\u0094\u0001\u0000\u0000\u0000\u0092\u0090"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0095"+
		"\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0095\u0096"+
		"\u0003\n\u0005\u0003\u0096\u0098\u0001\u0000\u0000\u0000\u0097\u0087\u0001"+
		"\u0000\u0000\u0000\u0098\u009b\u0001\u0000\u0000\u0000\u0099\u0097\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u000b\u0001"+
		"\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009c\u009d\u0004"+
		"\u0006\u0001\u0000\u009d\u00a0\u0003\u0014\n\u0000\u009e\u00a0\u0003\u0012"+
		"\t\u0000\u009f\u009c\u0001\u0000\u0000\u0000\u009f\u009e\u0001\u0000\u0000"+
		"\u0000\u00a0\r\u0001\u0000\u0000\u0000\u00a1\u00a7\u0005\u000b\u0000\u0000"+
		"\u00a2\u00a7\u0005\f\u0000\u0000\u00a3\u00a7\u0005\r\u0000\u0000\u00a4"+
		"\u00a7\u0005\u000e\u0000\u0000\u00a5\u00a7\u0005\u000f\u0000\u0000\u00a6"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a6\u00a2\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a7\u000f\u0001\u0000\u0000\u0000\u00a8"+
		"\u00ab\u0005\u0010\u0000\u0000\u00a9\u00ab\u0005\u0011\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00ab"+
		"\u0011\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005\u0001\u0000\u0000\u00ad"+
		"\u0013\u0001\u0000\u0000\u0000\u00ae\u00af\u0005\u0001\u0000\u0000\u00af"+
		"\u0015\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u0001\u0000\u0000\u00b1"+
		"\u0017\u0001\u0000\u0000\u0000\u0013\u001b\"(.7?IRZait\u0085\u008b\u0092"+
		"\u0099\u009f\u00a6\u00aa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}