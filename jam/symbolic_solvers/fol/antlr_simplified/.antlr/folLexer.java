// Generated from /home/fhoppe/Workspace/augmented_llm_playground/jam/symbolic_solvers/fol/antlr_simplified/fol.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class folLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IDENTIFIER=1, PREDICATE_START=2, PREMISES_START=3, CONCLUSION_START=4, 
		SEP=5, QUE=6, UND=7, LPAREN=8, RPAREN=9, NOT=10, CONJ=11, DISJ=12, IMPL=13, 
		BICOND=14, XOR=15, FORALL=16, EXISTS=17, IGNORE_NEWLINE=18, NEWLINE=19, 
		LINE_ESCAPE=20, WHITESPACE=21, SL_COMMENT=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IDENTIFIER", "LETTER", "DIGIT", "PREDICATE_START", "PREMISES_START", 
			"CONCLUSION_START", "SEP", "QUE", "UND", "LPAREN", "RPAREN", "NOT", "CONJ", 
			"DISJ", "IMPL", "BICOND", "XOR", "FORALL", "EXISTS", "IGNORE_NEWLINE", 
			"NEWLINE", "LINE_ESCAPE", "WHITESPACE", "SL_COMMENT"
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

	nesting = 0

	public folLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "fol.g4"; }

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
		case 9:
			LPAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 10:
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
		case 19:
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
		"\u0004\u0000\u0016\u00a1\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0001\u0000\u0001\u0000\u0004\u00004\b\u0000\u000b\u0000\f\u00005\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0003\u0013{\b\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0003\u0014\u0083"+
		"\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0003\u0015\u0089"+
		"\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0004"+
		"\u0016\u0090\b\u0016\u000b\u0016\f\u0016\u0091\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017"+
		"\u009b\b\u0017\n\u0017\f\u0017\u009e\t\u0017\u0001\u0017\u0001\u0017\u0000"+
		"\u0000\u0018\u0001\u0001\u0003\u0000\u0005\u0000\u0007\u0002\t\u0003\u000b"+
		"\u0004\r\u0005\u000f\u0006\u0011\u0007\u0013\b\u0015\t\u0017\n\u0019\u000b"+
		"\u001b\f\u001d\r\u001f\u000e!\u000f#\u0010%\u0011\'\u0012)\u0013+\u0014"+
		"-\u0015/\u0016\u0001\u0000\u0004\u0002\u0000AZaz\u0001\u000009\u0002\u0000"+
		"\t\t  \u0002\u0000\n\n\r\r\u00a5\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00013\u0001\u0000"+
		"\u0000\u0000\u00037\u0001\u0000\u0000\u0000\u00059\u0001\u0000\u0000\u0000"+
		"\u0007;\u0001\u0000\u0000\u0000\tG\u0001\u0000\u0000\u0000\u000bQ\u0001"+
		"\u0000\u0000\u0000\r]\u0001\u0000\u0000\u0000\u000f_\u0001\u0000\u0000"+
		"\u0000\u0011a\u0001\u0000\u0000\u0000\u0013c\u0001\u0000\u0000\u0000\u0015"+
		"f\u0001\u0000\u0000\u0000\u0017i\u0001\u0000\u0000\u0000\u0019k\u0001"+
		"\u0000\u0000\u0000\u001bm\u0001\u0000\u0000\u0000\u001do\u0001\u0000\u0000"+
		"\u0000\u001fq\u0001\u0000\u0000\u0000!s\u0001\u0000\u0000\u0000#u\u0001"+
		"\u0000\u0000\u0000%w\u0001\u0000\u0000\u0000\'z\u0001\u0000\u0000\u0000"+
		")\u0082\u0001\u0000\u0000\u0000+\u0086\u0001\u0000\u0000\u0000-\u008f"+
		"\u0001\u0000\u0000\u0000/\u0095\u0001\u0000\u0000\u000014\u0003\u0003"+
		"\u0001\u000024\u0003\u0005\u0002\u000031\u0001\u0000\u0000\u000032\u0001"+
		"\u0000\u0000\u000045\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u0000"+
		"56\u0001\u0000\u0000\u00006\u0002\u0001\u0000\u0000\u000078\u0007\u0000"+
		"\u0000\u00008\u0004\u0001\u0000\u0000\u00009:\u0007\u0001\u0000\u0000"+
		":\u0006\u0001\u0000\u0000\u0000;<\u0005P\u0000\u0000<=\u0005r\u0000\u0000"+
		"=>\u0005e\u0000\u0000>?\u0005d\u0000\u0000?@\u0005i\u0000\u0000@A\u0005"+
		"c\u0000\u0000AB\u0005a\u0000\u0000BC\u0005t\u0000\u0000CD\u0005e\u0000"+
		"\u0000DE\u0005s\u0000\u0000EF\u0005:\u0000\u0000F\b\u0001\u0000\u0000"+
		"\u0000GH\u0005P\u0000\u0000HI\u0005r\u0000\u0000IJ\u0005e\u0000\u0000"+
		"JK\u0005m\u0000\u0000KL\u0005i\u0000\u0000LM\u0005s\u0000\u0000MN\u0005"+
		"e\u0000\u0000NO\u0005s\u0000\u0000OP\u0005:\u0000\u0000P\n\u0001\u0000"+
		"\u0000\u0000QR\u0005C\u0000\u0000RS\u0005o\u0000\u0000ST\u0005n\u0000"+
		"\u0000TU\u0005c\u0000\u0000UV\u0005l\u0000\u0000VW\u0005u\u0000\u0000"+
		"WX\u0005s\u0000\u0000XY\u0005i\u0000\u0000YZ\u0005o\u0000\u0000Z[\u0005"+
		"n\u0000\u0000[\\\u0005:\u0000\u0000\\\f\u0001\u0000\u0000\u0000]^\u0005"+
		",\u0000\u0000^\u000e\u0001\u0000\u0000\u0000_`\u0005?\u0000\u0000`\u0010"+
		"\u0001\u0000\u0000\u0000ab\u0005_\u0000\u0000b\u0012\u0001\u0000\u0000"+
		"\u0000cd\u0005(\u0000\u0000de\u0006\t\u0000\u0000e\u0014\u0001\u0000\u0000"+
		"\u0000fg\u0005)\u0000\u0000gh\u0006\n\u0001\u0000h\u0016\u0001\u0000\u0000"+
		"\u0000ij\u0005\u00ac\u0000\u0000j\u0018\u0001\u0000\u0000\u0000kl\u0005"+
		"\u2227\u0000\u0000l\u001a\u0001\u0000\u0000\u0000mn\u0005\u2228\u0000"+
		"\u0000n\u001c\u0001\u0000\u0000\u0000op\u0005\u2192\u0000\u0000p\u001e"+
		"\u0001\u0000\u0000\u0000qr\u0005\u2194\u0000\u0000r \u0001\u0000\u0000"+
		"\u0000st\u0005\u2295\u0000\u0000t\"\u0001\u0000\u0000\u0000uv\u0005\u2200"+
		"\u0000\u0000v$\u0001\u0000\u0000\u0000wx\u0005\u2203\u0000\u0000x&\u0001"+
		"\u0000\u0000\u0000y{\u0005\r\u0000\u0000zy\u0001\u0000\u0000\u0000z{\u0001"+
		"\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0005\n\u0000\u0000}~\u0004"+
		"\u0013\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0080\u0006\u0013"+
		"\u0002\u0000\u0080(\u0001\u0000\u0000\u0000\u0081\u0083\u0005\r\u0000"+
		"\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000"+
		"\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0005\n\u0000\u0000"+
		"\u0085*\u0001\u0000\u0000\u0000\u0086\u0088\u0005\\\u0000\u0000\u0087"+
		"\u0089\u0005\r\u0000\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0088\u0089"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0005\n\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008d\u0006"+
		"\u0015\u0002\u0000\u008d,\u0001\u0000\u0000\u0000\u008e\u0090\u0007\u0002"+
		"\u0000\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000"+
		"\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0006\u0016"+
		"\u0002\u0000\u0094.\u0001\u0000\u0000\u0000\u0095\u0096\u0005:\u0000\u0000"+
		"\u0096\u0097\u0005:\u0000\u0000\u0097\u0098\u0005:\u0000\u0000\u0098\u009c"+
		"\u0001\u0000\u0000\u0000\u0099\u009b\b\u0003\u0000\u0000\u009a\u0099\u0001"+
		"\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009f\u0001"+
		"\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f\u00a0\u0006"+
		"\u0017\u0003\u0000\u00a00\u0001\u0000\u0000\u0000\b\u000035z\u0082\u0088"+
		"\u0091\u009c\u0004\u0001\t\u0000\u0001\n\u0001\u0006\u0000\u0000\u0000"+
		"\u0002\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}