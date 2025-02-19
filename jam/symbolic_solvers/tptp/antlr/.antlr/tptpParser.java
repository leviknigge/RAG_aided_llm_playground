// Generated from /home/fhoppe/Workspace/augmented_llm_playground/jam/symbolic_solvers/tptp/antlr/tptp.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class tptpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, IGNORE_NEWLINE=3, NEWLINE=4, LINE_ESCAPE=5, WHITESPACE=6, 
		SL_COMMENT=7, BL_COMMENT=8, FOF_ID=9, SEP=10, DOT=11, NOT=12, CONJ=13, 
		DISJ=14, IMPL=15, BICOND=16, XOR=17, FORALL=18, EXISTS=19, UPPER_ALPHA=20, 
		LOWER_ALPHA=21, DIGIT=22, ALPHA_NUMERIC=23, UPPER_WORD=24, LOWER_WORD=25, 
		LPAREN=26, RPAREN=27, LSQPAREN=28, RSQPAREN=29, COLON=30;
	public static final int
		RULE_formular_role = 0, RULE_start = 1, RULE_annotated_formula = 2, RULE_fof_annotated = 3, 
		RULE_fof_formula = 4, RULE_term = 5, RULE_ind_constant = 6, RULE_var_constant = 7, 
		RULE_pred_constant = 8, RULE_name = 9, RULE_bin_connective = 10, RULE_quantifier = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"formular_role", "start", "annotated_formula", "fof_annotated", "fof_formula", 
			"term", "ind_constant", "var_constant", "pred_constant", "name", "bin_connective", 
			"quantifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'axiom'", "'conjecture'", null, null, null, null, null, null, 
			"'fof'", "','", "'.'", "'~'", "'&'", "'|'", "'=>'", "'<=>'", "'<~>'", 
			"'!'", "'?'", null, null, null, null, null, null, "'('", "')'", "'['", 
			"']'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "IGNORE_NEWLINE", "NEWLINE", "LINE_ESCAPE", "WHITESPACE", 
			"SL_COMMENT", "BL_COMMENT", "FOF_ID", "SEP", "DOT", "NOT", "CONJ", "DISJ", 
			"IMPL", "BICOND", "XOR", "FORALL", "EXISTS", "UPPER_ALPHA", "LOWER_ALPHA", 
			"DIGIT", "ALPHA_NUMERIC", "UPPER_WORD", "LOWER_WORD", "LPAREN", "RPAREN", 
			"LSQPAREN", "RSQPAREN", "COLON"
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
	public String getGrammarFileName() { return "tptp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public tptpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Formular_roleContext extends ParserRuleContext {
		public Formular_roleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formular_role; }
	}

	public final Formular_roleContext formular_role() throws RecognitionException {
		Formular_roleContext _localctx = new Formular_roleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formular_role);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class StartContext extends ParserRuleContext {
		public List<Annotated_formulaContext> annotated_formula() {
			return getRuleContexts(Annotated_formulaContext.class);
		}
		public Annotated_formulaContext annotated_formula(int i) {
			return getRuleContext(Annotated_formulaContext.class,i);
		}
		public TerminalNode EOF() { return getToken(tptpParser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(tptpParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(tptpParser.NEWLINE, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_start);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(26);
				match(NEWLINE);
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(32);
			annotated_formula();
			setState(41);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(34); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(33);
						match(NEWLINE);
						}
						}
						setState(36); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NEWLINE );
					setState(38);
					annotated_formula();
					}
					} 
				}
				setState(43);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(44);
				match(NEWLINE);
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
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
	public static class Annotated_formulaContext extends ParserRuleContext {
		public Fof_annotatedContext fof_annotated() {
			return getRuleContext(Fof_annotatedContext.class,0);
		}
		public Annotated_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotated_formula; }
	}

	public final Annotated_formulaContext annotated_formula() throws RecognitionException {
		Annotated_formulaContext _localctx = new Annotated_formulaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_annotated_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			fof_annotated();
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
	public static class Fof_annotatedContext extends ParserRuleContext {
		public TerminalNode FOF_ID() { return getToken(tptpParser.FOF_ID, 0); }
		public TerminalNode LPAREN() { return getToken(tptpParser.LPAREN, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(tptpParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(tptpParser.SEP, i);
		}
		public Formular_roleContext formular_role() {
			return getRuleContext(Formular_roleContext.class,0);
		}
		public Fof_formulaContext fof_formula() {
			return getRuleContext(Fof_formulaContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(tptpParser.RPAREN, 0); }
		public TerminalNode DOT() { return getToken(tptpParser.DOT, 0); }
		public Fof_annotatedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fof_annotated; }
	}

	public final Fof_annotatedContext fof_annotated() throws RecognitionException {
		Fof_annotatedContext _localctx = new Fof_annotatedContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fof_annotated);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(FOF_ID);
			setState(55);
			match(LPAREN);
			setState(56);
			name();
			setState(57);
			match(SEP);
			setState(58);
			formular_role();
			setState(59);
			match(SEP);
			setState(60);
			fof_formula(0);
			setState(61);
			match(RPAREN);
			setState(62);
			match(DOT);
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
	public static class Fof_formulaContext extends ParserRuleContext {
		public Fof_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fof_formula; }
	 
		public Fof_formulaContext() { }
		public void copyFrom(Fof_formulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegationContext extends Fof_formulaContext {
		public TerminalNode NOT() { return getToken(tptpParser.NOT, 0); }
		public Fof_formulaContext fof_formula() {
			return getRuleContext(Fof_formulaContext.class,0);
		}
		public NegationContext(Fof_formulaContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Binary_connectiveContext extends Fof_formulaContext {
		public List<Fof_formulaContext> fof_formula() {
			return getRuleContexts(Fof_formulaContext.class);
		}
		public Fof_formulaContext fof_formula(int i) {
			return getRuleContext(Fof_formulaContext.class,i);
		}
		public Bin_connectiveContext bin_connective() {
			return getRuleContext(Bin_connectiveContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(tptpParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(tptpParser.NEWLINE, i);
		}
		public Binary_connectiveContext(Fof_formulaContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class QuantificationContext extends Fof_formulaContext {
		public QuantifierContext quantifier() {
			return getRuleContext(QuantifierContext.class,0);
		}
		public TerminalNode LSQPAREN() { return getToken(tptpParser.LSQPAREN, 0); }
		public List<Var_constantContext> var_constant() {
			return getRuleContexts(Var_constantContext.class);
		}
		public Var_constantContext var_constant(int i) {
			return getRuleContext(Var_constantContext.class,i);
		}
		public TerminalNode RSQPAREN() { return getToken(tptpParser.RSQPAREN, 0); }
		public TerminalNode COLON() { return getToken(tptpParser.COLON, 0); }
		public Fof_formulaContext fof_formula() {
			return getRuleContext(Fof_formulaContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(tptpParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(tptpParser.SEP, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(tptpParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(tptpParser.NEWLINE, i);
		}
		public QuantificationContext(Fof_formulaContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Atomic_formulaContext extends Fof_formulaContext {
		public Pred_constantContext pred_constant() {
			return getRuleContext(Pred_constantContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(tptpParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(tptpParser.RPAREN, 0); }
		public List<TerminalNode> SEP() { return getTokens(tptpParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(tptpParser.SEP, i);
		}
		public Atomic_formulaContext(Fof_formulaContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesisContext extends Fof_formulaContext {
		public TerminalNode LPAREN() { return getToken(tptpParser.LPAREN, 0); }
		public Fof_formulaContext fof_formula() {
			return getRuleContext(Fof_formulaContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(tptpParser.RPAREN, 0); }
		public ParenthesisContext(Fof_formulaContext ctx) { copyFrom(ctx); }
	}

	public final Fof_formulaContext fof_formula() throws RecognitionException {
		return fof_formula(0);
	}

	private Fof_formulaContext fof_formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Fof_formulaContext _localctx = new Fof_formulaContext(_ctx, _parentState);
		Fof_formulaContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_fof_formula, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOWER_WORD:
				{
				_localctx = new Atomic_formulaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(65);
				pred_constant();
				setState(66);
				match(LPAREN);
				setState(67);
				term();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(68);
					match(SEP);
					setState(69);
					term();
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(75);
				match(RPAREN);
				}
				break;
			case LPAREN:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				match(LPAREN);
				setState(78);
				fof_formula(0);
				setState(79);
				match(RPAREN);
				}
				break;
			case NOT:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				match(NOT);
				setState(82);
				fof_formula(3);
				}
				break;
			case FORALL:
			case EXISTS:
				{
				_localctx = new QuantificationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				quantifier();
				setState(84);
				match(LSQPAREN);
				setState(85);
				var_constant();
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(86);
					match(SEP);
					setState(87);
					var_constant();
					}
					}
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(93);
				match(RSQPAREN);
				setState(94);
				match(COLON);
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(95);
					match(NEWLINE);
					}
					}
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(101);
				fof_formula(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(123);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Binary_connectiveContext(new Fof_formulaContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_fof_formula);
					setState(105);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(106);
						match(NEWLINE);
						}
						}
						setState(111);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(112);
					bin_connective();
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(113);
						match(NEWLINE);
						}
						}
						setState(118);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(119);
					fof_formula(3);
					}
					} 
				}
				setState(125);
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
		enterRule(_localctx, 10, RULE_term);
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOWER_WORD:
				_localctx = new Ind_valueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				ind_constant();
				}
				break;
			case UPPER_WORD:
				_localctx = new Var_valueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				var_constant();
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
		public TerminalNode LOWER_WORD() { return getToken(tptpParser.LOWER_WORD, 0); }
		public Ind_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ind_constant; }
	}

	public final Ind_constantContext ind_constant() throws RecognitionException {
		Ind_constantContext _localctx = new Ind_constantContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ind_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(LOWER_WORD);
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
		public TerminalNode UPPER_WORD() { return getToken(tptpParser.UPPER_WORD, 0); }
		public Var_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_constant; }
	}

	public final Var_constantContext var_constant() throws RecognitionException {
		Var_constantContext _localctx = new Var_constantContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(UPPER_WORD);
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
		public TerminalNode LOWER_WORD() { return getToken(tptpParser.LOWER_WORD, 0); }
		public Pred_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pred_constant; }
	}

	public final Pred_constantContext pred_constant() throws RecognitionException {
		Pred_constantContext _localctx = new Pred_constantContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pred_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(LOWER_WORD);
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
	public static class NameContext extends ParserRuleContext {
		public TerminalNode LOWER_WORD() { return getToken(tptpParser.LOWER_WORD, 0); }
		public List<TerminalNode> DIGIT() { return getTokens(tptpParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(tptpParser.DIGIT, i);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_name);
		int _la;
		try {
			setState(142);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOWER_WORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				match(LOWER_WORD);
				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(137);
					match(DIGIT);
					}
					}
					setState(140); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGIT );
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
		public TerminalNode IMPL() { return getToken(tptpParser.IMPL, 0); }
		public ImplContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DisjContext extends Bin_connectiveContext {
		public TerminalNode DISJ() { return getToken(tptpParser.DISJ, 0); }
		public DisjContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConjContext extends Bin_connectiveContext {
		public TerminalNode CONJ() { return getToken(tptpParser.CONJ, 0); }
		public ConjContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BicondContext extends Bin_connectiveContext {
		public TerminalNode BICOND() { return getToken(tptpParser.BICOND, 0); }
		public BicondContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XorContext extends Bin_connectiveContext {
		public TerminalNode XOR() { return getToken(tptpParser.XOR, 0); }
		public XorContext(Bin_connectiveContext ctx) { copyFrom(ctx); }
	}

	public final Bin_connectiveContext bin_connective() throws RecognitionException {
		Bin_connectiveContext _localctx = new Bin_connectiveContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bin_connective);
		try {
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONJ:
				_localctx = new ConjContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				match(CONJ);
				}
				break;
			case DISJ:
				_localctx = new DisjContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				match(DISJ);
				}
				break;
			case IMPL:
				_localctx = new ImplContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				match(IMPL);
				}
				break;
			case BICOND:
				_localctx = new BicondContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				match(BICOND);
				}
				break;
			case XOR:
				_localctx = new XorContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(148);
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
		public TerminalNode FORALL() { return getToken(tptpParser.FORALL, 0); }
		public ForallContext(QuantifierContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExistsContext extends QuantifierContext {
		public TerminalNode EXISTS() { return getToken(tptpParser.EXISTS, 0); }
		public ExistsContext(QuantifierContext ctx) { copyFrom(ctx); }
	}

	public final QuantifierContext quantifier() throws RecognitionException {
		QuantifierContext _localctx = new QuantifierContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_quantifier);
		try {
			setState(153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FORALL:
				_localctx = new ForallContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				match(FORALL);
				}
				break;
			case EXISTS:
				_localctx = new ExistsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return fof_formula_sempred((Fof_formulaContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean fof_formula_sempred(Fof_formulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001e\u009c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001\u001c\b\u0001\n\u0001"+
		"\f\u0001\u001f\t\u0001\u0001\u0001\u0001\u0001\u0004\u0001#\b\u0001\u000b"+
		"\u0001\f\u0001$\u0001\u0001\u0005\u0001(\b\u0001\n\u0001\f\u0001+\t\u0001"+
		"\u0001\u0001\u0005\u0001.\b\u0001\n\u0001\f\u00011\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004G\b\u0004\n\u0004\f\u0004J\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004Y\b\u0004\n\u0004\f\u0004\\\t\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004a\b\u0004\n\u0004\f\u0004d\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004h\b\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"l\b\u0004\n\u0004\f\u0004o\t\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"s\b\u0004\n\u0004\f\u0004v\t\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"z\b\u0004\n\u0004\f\u0004}\t\u0004\u0001\u0005\u0001\u0005\u0003\u0005"+
		"\u0081\b\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0004\t\u008b\b\t\u000b\t\f\t\u008c\u0003\t\u008f"+
		"\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0096\b\n\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u009a\b\u000b\u0001\u000b\u0000\u0001\b\f\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0001\u0001"+
		"\u0000\u0001\u0002\u00a4\u0000\u0018\u0001\u0000\u0000\u0000\u0002\u001d"+
		"\u0001\u0000\u0000\u0000\u00044\u0001\u0000\u0000\u0000\u00066\u0001\u0000"+
		"\u0000\u0000\bg\u0001\u0000\u0000\u0000\n\u0080\u0001\u0000\u0000\u0000"+
		"\f\u0082\u0001\u0000\u0000\u0000\u000e\u0084\u0001\u0000\u0000\u0000\u0010"+
		"\u0086\u0001\u0000\u0000\u0000\u0012\u008e\u0001\u0000\u0000\u0000\u0014"+
		"\u0095\u0001\u0000\u0000\u0000\u0016\u0099\u0001\u0000\u0000\u0000\u0018"+
		"\u0019\u0007\u0000\u0000\u0000\u0019\u0001\u0001\u0000\u0000\u0000\u001a"+
		"\u001c\u0005\u0004\u0000\u0000\u001b\u001a\u0001\u0000\u0000\u0000\u001c"+
		"\u001f\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d"+
		"\u001e\u0001\u0000\u0000\u0000\u001e \u0001\u0000\u0000\u0000\u001f\u001d"+
		"\u0001\u0000\u0000\u0000 )\u0003\u0004\u0002\u0000!#\u0005\u0004\u0000"+
		"\u0000\"!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$\"\u0001\u0000"+
		"\u0000\u0000$%\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&(\u0003"+
		"\u0004\u0002\u0000\'\"\u0001\u0000\u0000\u0000(+\u0001\u0000\u0000\u0000"+
		")\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*/\u0001\u0000\u0000"+
		"\u0000+)\u0001\u0000\u0000\u0000,.\u0005\u0004\u0000\u0000-,\u0001\u0000"+
		"\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001"+
		"\u0000\u0000\u000002\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u0000"+
		"23\u0005\u0000\u0000\u00013\u0003\u0001\u0000\u0000\u000045\u0003\u0006"+
		"\u0003\u00005\u0005\u0001\u0000\u0000\u000067\u0005\t\u0000\u000078\u0005"+
		"\u001a\u0000\u000089\u0003\u0012\t\u00009:\u0005\n\u0000\u0000:;\u0003"+
		"\u0000\u0000\u0000;<\u0005\n\u0000\u0000<=\u0003\b\u0004\u0000=>\u0005"+
		"\u001b\u0000\u0000>?\u0005\u000b\u0000\u0000?\u0007\u0001\u0000\u0000"+
		"\u0000@A\u0006\u0004\uffff\uffff\u0000AB\u0003\u0010\b\u0000BC\u0005\u001a"+
		"\u0000\u0000CH\u0003\n\u0005\u0000DE\u0005\n\u0000\u0000EG\u0003\n\u0005"+
		"\u0000FD\u0001\u0000\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000"+
		"\u0000\u0000HI\u0001\u0000\u0000\u0000IK\u0001\u0000\u0000\u0000JH\u0001"+
		"\u0000\u0000\u0000KL\u0005\u001b\u0000\u0000Lh\u0001\u0000\u0000\u0000"+
		"MN\u0005\u001a\u0000\u0000NO\u0003\b\u0004\u0000OP\u0005\u001b\u0000\u0000"+
		"Ph\u0001\u0000\u0000\u0000QR\u0005\f\u0000\u0000Rh\u0003\b\u0004\u0003"+
		"ST\u0003\u0016\u000b\u0000TU\u0005\u001c\u0000\u0000UZ\u0003\u000e\u0007"+
		"\u0000VW\u0005\n\u0000\u0000WY\u0003\u000e\u0007\u0000XV\u0001\u0000\u0000"+
		"\u0000Y\\\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[]\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]^\u0005"+
		"\u001d\u0000\u0000^b\u0005\u001e\u0000\u0000_a\u0005\u0004\u0000\u0000"+
		"`_\u0001\u0000\u0000\u0000ad\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000"+
		"\u0000bc\u0001\u0000\u0000\u0000ce\u0001\u0000\u0000\u0000db\u0001\u0000"+
		"\u0000\u0000ef\u0003\b\u0004\u0001fh\u0001\u0000\u0000\u0000g@\u0001\u0000"+
		"\u0000\u0000gM\u0001\u0000\u0000\u0000gQ\u0001\u0000\u0000\u0000gS\u0001"+
		"\u0000\u0000\u0000h{\u0001\u0000\u0000\u0000im\n\u0002\u0000\u0000jl\u0005"+
		"\u0004\u0000\u0000kj\u0001\u0000\u0000\u0000lo\u0001\u0000\u0000\u0000"+
		"mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000np\u0001\u0000\u0000"+
		"\u0000om\u0001\u0000\u0000\u0000pt\u0003\u0014\n\u0000qs\u0005\u0004\u0000"+
		"\u0000rq\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000"+
		"\u0000\u0000tu\u0001\u0000\u0000\u0000uw\u0001\u0000\u0000\u0000vt\u0001"+
		"\u0000\u0000\u0000wx\u0003\b\u0004\u0003xz\u0001\u0000\u0000\u0000yi\u0001"+
		"\u0000\u0000\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000"+
		"{|\u0001\u0000\u0000\u0000|\t\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000"+
		"\u0000~\u0081\u0003\f\u0006\u0000\u007f\u0081\u0003\u000e\u0007\u0000"+
		"\u0080~\u0001\u0000\u0000\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081"+
		"\u000b\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u0019\u0000\u0000\u0083"+
		"\r\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u0018\u0000\u0000\u0085\u000f"+
		"\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u0019\u0000\u0000\u0087\u0011"+
		"\u0001\u0000\u0000\u0000\u0088\u008f\u0005\u0019\u0000\u0000\u0089\u008b"+
		"\u0005\u0016\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008b\u008c"+
		"\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c\u008d"+
		"\u0001\u0000\u0000\u0000\u008d\u008f\u0001\u0000\u0000\u0000\u008e\u0088"+
		"\u0001\u0000\u0000\u0000\u008e\u008a\u0001\u0000\u0000\u0000\u008f\u0013"+
		"\u0001\u0000\u0000\u0000\u0090\u0096\u0005\r\u0000\u0000\u0091\u0096\u0005"+
		"\u000e\u0000\u0000\u0092\u0096\u0005\u000f\u0000\u0000\u0093\u0096\u0005"+
		"\u0010\u0000\u0000\u0094\u0096\u0005\u0011\u0000\u0000\u0095\u0090\u0001"+
		"\u0000\u0000\u0000\u0095\u0091\u0001\u0000\u0000\u0000\u0095\u0092\u0001"+
		"\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0095\u0094\u0001"+
		"\u0000\u0000\u0000\u0096\u0015\u0001\u0000\u0000\u0000\u0097\u009a\u0005"+
		"\u0012\u0000\u0000\u0098\u009a\u0005\u0013\u0000\u0000\u0099\u0097\u0001"+
		"\u0000\u0000\u0000\u0099\u0098\u0001\u0000\u0000\u0000\u009a\u0017\u0001"+
		"\u0000\u0000\u0000\u0010\u001d$)/HZbgmt{\u0080\u008c\u008e\u0095\u0099";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}