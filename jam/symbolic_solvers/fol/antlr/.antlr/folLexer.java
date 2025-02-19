// Generated from /home/fhoppe/Workspace/augmented_llm_playground/jam/symbolic_solvers/fol/antlr/fol.g4 by ANTLR 4.13.1
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
		IGNORE_NEWLINE=1, NEWLINE=2, LINE_ESCAPE=3, WHITESPACE=4, SL_COMMENT=5, 
		IDENTIFIER=6, PREDICATE_START=7, PREMISES_START=8, CONCLUSION_START=9, 
		SEP=10, QUE=11, UND=12, LPAREN=13, RPAREN=14, NOT=15, CONJ=16, DISJ=17, 
		IMPL=18, BICOND=19, XOR=20, FORALL=21, EXISTS=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IGNORE_NEWLINE", "NEWLINE", "LINE_ESCAPE", "WHITESPACE", "SL_COMMENT", 
			"IDENTIFIER", "LETTER", "DIGIT", "PREDICATE_START", "PREMISES_START", 
			"CONCLUSION_START", "SEP", "QUE", "UND", "LPAREN", "RPAREN", "NOT", "CONJ", 
			"DISJ", "IMPL", "BICOND", "XOR", "FORALL", "EXISTS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'Predicates:'", "'Premises:'", 
			"'Conclusion:'", "','", "'?'", "'_'", "'('", "')'", "'\\u00AC'", "'\\u2227'", 
			"'\\u2228'", "'\\u2192'", "'\\u2194'", "'\\u2295'", "'\\u2200'", "'\\u2203'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IGNORE_NEWLINE", "NEWLINE", "LINE_ESCAPE", "WHITESPACE", "SL_COMMENT", 
			"IDENTIFIER", "PREDICATE_START", "PREMISES_START", "CONCLUSION_START", 
			"SEP", "QUE", "UND", "LPAREN", "RPAREN", "NOT", "CONJ", "DISJ", "IMPL", 
			"BICOND", "XOR", "FORALL", "EXISTS"
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
		case 14:
			LPAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 15:
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
		case 0:
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
		"\u0001\u0000\u0003\u00003\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0003\u0001;\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0003\u0002A\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0004\u0003H\b\u0003"+
		"\u000b\u0003\f\u0003I\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004S\b\u0004\n\u0004\f\u0004"+
		"V\t\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0004\u0005"+
		"\\\b\u0005\u000b\u0005\f\u0005]\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0000\u0000\u0018\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0000\u000f\u0000\u0011\u0007"+
		"\u0013\b\u0015\t\u0017\n\u0019\u000b\u001b\f\u001d\r\u001f\u000e!\u000f"+
		"#\u0010%\u0011\'\u0012)\u0013+\u0014-\u0015/\u0016\u0001\u0000\u0004\u0002"+
		"\u0000\t\t  \u0002\u0000\n\n\r\r\u0002\u0000AZaz\u0001\u000009\u00a5\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00012\u0001\u0000\u0000\u0000\u0003:\u0001\u0000"+
		"\u0000\u0000\u0005>\u0001\u0000\u0000\u0000\u0007G\u0001\u0000\u0000\u0000"+
		"\tM\u0001\u0000\u0000\u0000\u000b[\u0001\u0000\u0000\u0000\r_\u0001\u0000"+
		"\u0000\u0000\u000fa\u0001\u0000\u0000\u0000\u0011c\u0001\u0000\u0000\u0000"+
		"\u0013o\u0001\u0000\u0000\u0000\u0015y\u0001\u0000\u0000\u0000\u0017\u0085"+
		"\u0001\u0000\u0000\u0000\u0019\u0087\u0001\u0000\u0000\u0000\u001b\u0089"+
		"\u0001\u0000\u0000\u0000\u001d\u008b\u0001\u0000\u0000\u0000\u001f\u008e"+
		"\u0001\u0000\u0000\u0000!\u0091\u0001\u0000\u0000\u0000#\u0093\u0001\u0000"+
		"\u0000\u0000%\u0095\u0001\u0000\u0000\u0000\'\u0097\u0001\u0000\u0000"+
		"\u0000)\u0099\u0001\u0000\u0000\u0000+\u009b\u0001\u0000\u0000\u0000-"+
		"\u009d\u0001\u0000\u0000\u0000/\u009f\u0001\u0000\u0000\u000013\u0005"+
		"\r\u0000\u000021\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000034\u0001"+
		"\u0000\u0000\u000045\u0005\n\u0000\u000056\u0004\u0000\u0000\u000067\u0001"+
		"\u0000\u0000\u000078\u0006\u0000\u0000\u00008\u0002\u0001\u0000\u0000"+
		"\u00009;\u0005\r\u0000\u0000:9\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000"+
		"\u0000;<\u0001\u0000\u0000\u0000<=\u0005\n\u0000\u0000=\u0004\u0001\u0000"+
		"\u0000\u0000>@\u0005\\\u0000\u0000?A\u0005\r\u0000\u0000@?\u0001\u0000"+
		"\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0005"+
		"\n\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0006\u0002\u0000\u0000E\u0006"+
		"\u0001\u0000\u0000\u0000FH\u0007\u0000\u0000\u0000GF\u0001\u0000\u0000"+
		"\u0000HI\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000IJ\u0001\u0000"+
		"\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0006\u0003\u0000\u0000L\b\u0001"+
		"\u0000\u0000\u0000MN\u0005:\u0000\u0000NO\u0005:\u0000\u0000OP\u0005:"+
		"\u0000\u0000PT\u0001\u0000\u0000\u0000QS\b\u0001\u0000\u0000RQ\u0001\u0000"+
		"\u0000\u0000SV\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001"+
		"\u0000\u0000\u0000UW\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000"+
		"WX\u0006\u0004\u0001\u0000X\n\u0001\u0000\u0000\u0000Y\\\u0003\r\u0006"+
		"\u0000Z\\\u0003\u000f\u0007\u0000[Y\u0001\u0000\u0000\u0000[Z\u0001\u0000"+
		"\u0000\u0000\\]\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001"+
		"\u0000\u0000\u0000^\f\u0001\u0000\u0000\u0000_`\u0007\u0002\u0000\u0000"+
		"`\u000e\u0001\u0000\u0000\u0000ab\u0007\u0003\u0000\u0000b\u0010\u0001"+
		"\u0000\u0000\u0000cd\u0005P\u0000\u0000de\u0005r\u0000\u0000ef\u0005e"+
		"\u0000\u0000fg\u0005d\u0000\u0000gh\u0005i\u0000\u0000hi\u0005c\u0000"+
		"\u0000ij\u0005a\u0000\u0000jk\u0005t\u0000\u0000kl\u0005e\u0000\u0000"+
		"lm\u0005s\u0000\u0000mn\u0005:\u0000\u0000n\u0012\u0001\u0000\u0000\u0000"+
		"op\u0005P\u0000\u0000pq\u0005r\u0000\u0000qr\u0005e\u0000\u0000rs\u0005"+
		"m\u0000\u0000st\u0005i\u0000\u0000tu\u0005s\u0000\u0000uv\u0005e\u0000"+
		"\u0000vw\u0005s\u0000\u0000wx\u0005:\u0000\u0000x\u0014\u0001\u0000\u0000"+
		"\u0000yz\u0005C\u0000\u0000z{\u0005o\u0000\u0000{|\u0005n\u0000\u0000"+
		"|}\u0005c\u0000\u0000}~\u0005l\u0000\u0000~\u007f\u0005u\u0000\u0000\u007f"+
		"\u0080\u0005s\u0000\u0000\u0080\u0081\u0005i\u0000\u0000\u0081\u0082\u0005"+
		"o\u0000\u0000\u0082\u0083\u0005n\u0000\u0000\u0083\u0084\u0005:\u0000"+
		"\u0000\u0084\u0016\u0001\u0000\u0000\u0000\u0085\u0086\u0005,\u0000\u0000"+
		"\u0086\u0018\u0001\u0000\u0000\u0000\u0087\u0088\u0005?\u0000\u0000\u0088"+
		"\u001a\u0001\u0000\u0000\u0000\u0089\u008a\u0005_\u0000\u0000\u008a\u001c"+
		"\u0001\u0000\u0000\u0000\u008b\u008c\u0005(\u0000\u0000\u008c\u008d\u0006"+
		"\u000e\u0002\u0000\u008d\u001e\u0001\u0000\u0000\u0000\u008e\u008f\u0005"+
		")\u0000\u0000\u008f\u0090\u0006\u000f\u0003\u0000\u0090 \u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0005\u00ac\u0000\u0000\u0092\"\u0001\u0000\u0000\u0000"+
		"\u0093\u0094\u0005\u2227\u0000\u0000\u0094$\u0001\u0000\u0000\u0000\u0095"+
		"\u0096\u0005\u2228\u0000\u0000\u0096&\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0005\u2192\u0000\u0000\u0098(\u0001\u0000\u0000\u0000\u0099\u009a\u0005"+
		"\u2194\u0000\u0000\u009a*\u0001\u0000\u0000\u0000\u009b\u009c\u0005\u2295"+
		"\u0000\u0000\u009c,\u0001\u0000\u0000\u0000\u009d\u009e\u0005\u2200\u0000"+
		"\u0000\u009e.\u0001\u0000\u0000\u0000\u009f\u00a0\u0005\u2203\u0000\u0000"+
		"\u00a00\u0001\u0000\u0000\u0000\b\u00002:@IT[]\u0004\u0006\u0000\u0000"+
		"\u0000\u0002\u0000\u0001\u000e\u0000\u0001\u000f\u0001";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}