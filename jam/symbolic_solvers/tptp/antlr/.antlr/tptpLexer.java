// Generated from /home/fhoppe/Workspace/augmented_llm_playground/jam/symbolic_solvers/tptp/antlr/tptp.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class tptpLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "IGNORE_NEWLINE", "NEWLINE", "LINE_ESCAPE", "WHITESPACE", 
			"SL_COMMENT", "BL_COMMENT", "FOF_ID", "SEP", "DOT", "NOT", "CONJ", "DISJ", 
			"IMPL", "BICOND", "XOR", "FORALL", "EXISTS", "UPPER_ALPHA", "LOWER_ALPHA", 
			"DIGIT", "ALPHA_NUMERIC", "UPPER_WORD", "LOWER_WORD", "LPAREN", "RPAREN", 
			"LSQPAREN", "RSQPAREN", "COLON"
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

	nesting = 0

	public tptpLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "tptp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 25:
			LPAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 26:
			RPAREN_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void LPAREN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			self.nesting +=1
			break;
		}
	}
	private void RPAREN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			self.nesting -=1
			break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return IGNORE_NEWLINE_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean IGNORE_NEWLINE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return self.nesting>0;
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0000\u001e\u00c3\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0003\u0002P\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0003\u0003X\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0003\u0004^\b\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0004\u0005e\b\u0005\u000b\u0005"+
		"\f\u0005f\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0005\u0006"+
		"m\b\u0006\n\u0006\f\u0006p\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007x\b\u0007\n\u0007\f\u0007"+
		"{\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00a8\b\u0016"+
		"\u0001\u0017\u0001\u0017\u0005\u0017\u00ac\b\u0017\n\u0017\f\u0017\u00af"+
		"\t\u0017\u0001\u0018\u0001\u0018\u0005\u0018\u00b3\b\u0018\n\u0018\f\u0018"+
		"\u00b6\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001y\u0000\u001e\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d"+
		";\u001e\u0001\u0000\u0005\u0002\u0000\t\t  \u0002\u0000\n\n\r\r\u0001"+
		"\u0000AZ\u0001\u0000az\u0001\u000009\u00cc\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000"+
		"\u0000;\u0001\u0000\u0000\u0000\u0001=\u0001\u0000\u0000\u0000\u0003C"+
		"\u0001\u0000\u0000\u0000\u0005O\u0001\u0000\u0000\u0000\u0007W\u0001\u0000"+
		"\u0000\u0000\t[\u0001\u0000\u0000\u0000\u000bd\u0001\u0000\u0000\u0000"+
		"\rj\u0001\u0000\u0000\u0000\u000fs\u0001\u0000\u0000\u0000\u0011\u0081"+
		"\u0001\u0000\u0000\u0000\u0013\u0085\u0001\u0000\u0000\u0000\u0015\u0087"+
		"\u0001\u0000\u0000\u0000\u0017\u0089\u0001\u0000\u0000\u0000\u0019\u008b"+
		"\u0001\u0000\u0000\u0000\u001b\u008d\u0001\u0000\u0000\u0000\u001d\u008f"+
		"\u0001\u0000\u0000\u0000\u001f\u0092\u0001\u0000\u0000\u0000!\u0096\u0001"+
		"\u0000\u0000\u0000#\u009a\u0001\u0000\u0000\u0000%\u009c\u0001\u0000\u0000"+
		"\u0000\'\u009e\u0001\u0000\u0000\u0000)\u00a0\u0001\u0000\u0000\u0000"+
		"+\u00a2\u0001\u0000\u0000\u0000-\u00a7\u0001\u0000\u0000\u0000/\u00a9"+
		"\u0001\u0000\u0000\u00001\u00b0\u0001\u0000\u0000\u00003\u00b7\u0001\u0000"+
		"\u0000\u00005\u00ba\u0001\u0000\u0000\u00007\u00bd\u0001\u0000\u0000\u0000"+
		"9\u00bf\u0001\u0000\u0000\u0000;\u00c1\u0001\u0000\u0000\u0000=>\u0005"+
		"a\u0000\u0000>?\u0005x\u0000\u0000?@\u0005i\u0000\u0000@A\u0005o\u0000"+
		"\u0000AB\u0005m\u0000\u0000B\u0002\u0001\u0000\u0000\u0000CD\u0005c\u0000"+
		"\u0000DE\u0005o\u0000\u0000EF\u0005n\u0000\u0000FG\u0005j\u0000\u0000"+
		"GH\u0005e\u0000\u0000HI\u0005c\u0000\u0000IJ\u0005t\u0000\u0000JK\u0005"+
		"u\u0000\u0000KL\u0005r\u0000\u0000LM\u0005e\u0000\u0000M\u0004\u0001\u0000"+
		"\u0000\u0000NP\u0005\r\u0000\u0000ON\u0001\u0000\u0000\u0000OP\u0001\u0000"+
		"\u0000\u0000PQ\u0001\u0000\u0000\u0000QR\u0005\n\u0000\u0000RS\u0004\u0002"+
		"\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0006\u0002\u0000\u0000U\u0006"+
		"\u0001\u0000\u0000\u0000VX\u0005\r\u0000\u0000WV\u0001\u0000\u0000\u0000"+
		"WX\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0005\n\u0000\u0000"+
		"Z\b\u0001\u0000\u0000\u0000[]\u0005\\\u0000\u0000\\^\u0005\r\u0000\u0000"+
		"]\\\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_`\u0005\n\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0006\u0004\u0000"+
		"\u0000b\n\u0001\u0000\u0000\u0000ce\u0007\u0000\u0000\u0000dc\u0001\u0000"+
		"\u0000\u0000ef\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001"+
		"\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hi\u0006\u0005\u0000\u0000"+
		"i\f\u0001\u0000\u0000\u0000jn\u0005%\u0000\u0000km\b\u0001\u0000\u0000"+
		"lk\u0001\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000"+
		"\u0000no\u0001\u0000\u0000\u0000oq\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000qr\u0006\u0006\u0001\u0000r\u000e\u0001\u0000\u0000\u0000"+
		"st\u0005/\u0000\u0000tu\u0005*\u0000\u0000uy\u0001\u0000\u0000\u0000v"+
		"x\t\u0000\u0000\u0000wv\u0001\u0000\u0000\u0000x{\u0001\u0000\u0000\u0000"+
		"yz\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000z|\u0001\u0000\u0000"+
		"\u0000{y\u0001\u0000\u0000\u0000|}\u0005*\u0000\u0000}~\u0005/\u0000\u0000"+
		"~\u007f\u0001\u0000\u0000\u0000\u007f\u0080\u0006\u0007\u0001\u0000\u0080"+
		"\u0010\u0001\u0000\u0000\u0000\u0081\u0082\u0005f\u0000\u0000\u0082\u0083"+
		"\u0005o\u0000\u0000\u0083\u0084\u0005f\u0000\u0000\u0084\u0012\u0001\u0000"+
		"\u0000\u0000\u0085\u0086\u0005,\u0000\u0000\u0086\u0014\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0005.\u0000\u0000\u0088\u0016\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0005~\u0000\u0000\u008a\u0018\u0001\u0000\u0000\u0000\u008b"+
		"\u008c\u0005&\u0000\u0000\u008c\u001a\u0001\u0000\u0000\u0000\u008d\u008e"+
		"\u0005|\u0000\u0000\u008e\u001c\u0001\u0000\u0000\u0000\u008f\u0090\u0005"+
		"=\u0000\u0000\u0090\u0091\u0005>\u0000\u0000\u0091\u001e\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0005<\u0000\u0000\u0093\u0094\u0005=\u0000\u0000\u0094"+
		"\u0095\u0005>\u0000\u0000\u0095 \u0001\u0000\u0000\u0000\u0096\u0097\u0005"+
		"<\u0000\u0000\u0097\u0098\u0005~\u0000\u0000\u0098\u0099\u0005>\u0000"+
		"\u0000\u0099\"\u0001\u0000\u0000\u0000\u009a\u009b\u0005!\u0000\u0000"+
		"\u009b$\u0001\u0000\u0000\u0000\u009c\u009d\u0005?\u0000\u0000\u009d&"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0007\u0002\u0000\u0000\u009f(\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a1\u0007\u0003\u0000\u0000\u00a1*\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a3\u0007\u0004\u0000\u0000\u00a3,\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a8\u0003\'\u0013\u0000\u00a5\u00a8\u0003)\u0014\u0000"+
		"\u00a6\u00a8\u0003+\u0015\u0000\u00a7\u00a4\u0001\u0000\u0000\u0000\u00a7"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8"+
		".\u0001\u0000\u0000\u0000\u00a9\u00ad\u0003\'\u0013\u0000\u00aa\u00ac"+
		"\u0003-\u0016\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ac\u00af\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001"+
		"\u0000\u0000\u0000\u00ae0\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000"+
		"\u0000\u0000\u00b0\u00b4\u0003)\u0014\u0000\u00b1\u00b3\u0003-\u0016\u0000"+
		"\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b52\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0005(\u0000\u0000\u00b8\u00b9\u0006\u0019\u0002\u0000\u00b94\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bb\u0005)\u0000\u0000\u00bb\u00bc\u0006\u001a"+
		"\u0003\u0000\u00bc6\u0001\u0000\u0000\u0000\u00bd\u00be\u0005[\u0000\u0000"+
		"\u00be8\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005]\u0000\u0000\u00c0:"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005:\u0000\u0000\u00c2<\u0001\u0000"+
		"\u0000\u0000\n\u0000OW]fny\u00a7\u00ad\u00b4\u0004\u0006\u0000\u0000\u0000"+
		"\u0002\u0000\u0001\u0019\u0000\u0001\u001a\u0001";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}