package QueryManager.queryprocessor.gen;// Generated from K:/Java/MultiWearANTLRv1.0\MultiWearQueryLanguage.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MultiWearQueryLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, IDENTIFIER=29, INT=30, WS=31;
	public static final int
		RULE_query = 0, RULE_dataStorage = 1, RULE_selectQuery = 2, RULE_retrieveSpecification = 3, 
		RULE_aggregationSpecification = 4, RULE_aggregation = 5, RULE_aggregationType = 6, 
		RULE_sensorList = 7, RULE_whereClause = 8, RULE_expression = 9, RULE_conditionalExpression = 10, 
		RULE_operator = 11, RULE_periodicityClause = 12, RULE_lifetimeClause = 13, 
		RULE_whenClause = 14, RULE_counter = 15, RULE_sensor = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"query", "dataStorage", "selectQuery", "retrieveSpecification", "aggregationSpecification", 
			"aggregation", "aggregationType", "sensorList", "whereClause", "expression", 
			"conditionalExpression", "operator", "periodicityClause", "lifetimeClause", 
			"whenClause", "counter", "sensor"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'CREATE STORAGE'", "'AS'", "'SELECT'", "'FROM wearables'", "';'", 
			"'*'", "','", "'('", "')'", "'MIN'", "'MAX'", "'AVG'", "'SUM'", "'WHERE'", 
			"'='", "'!='", "'<'", "'>'", "'<='", "'>='", "'EVERY'", "'ms'", "'s'", 
			"'m'", "'h'", "'FOR'", "'WHEN'", "'MEASUREMENTS'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "IDENTIFIER", "INT", "WS"
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
	public String getGrammarFileName() { return "MultiWearQueryLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MultiWearQueryLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class QueryContext extends ParserRuleContext {
		public SelectQueryContext selectQuery() {
			return getRuleContext(SelectQueryContext.class,0);
		}
		public DataStorageContext dataStorage() {
			return getRuleContext(DataStorageContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(34);
				dataStorage();
				}
			}

			setState(37);
			selectQuery();
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

	public static class DataStorageContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MultiWearQueryLanguageParser.IDENTIFIER, 0); }
		public DataStorageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataStorage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterDataStorage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitDataStorage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitDataStorage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataStorageContext dataStorage() throws RecognitionException {
		DataStorageContext _localctx = new DataStorageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dataStorage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__0);
			setState(40);
			match(IDENTIFIER);
			setState(41);
			match(T__1);
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

	public static class SelectQueryContext extends ParserRuleContext {
		public RetrieveSpecificationContext retrieveSpecification() {
			return getRuleContext(RetrieveSpecificationContext.class,0);
		}
		public AggregationSpecificationContext aggregationSpecification() {
			return getRuleContext(AggregationSpecificationContext.class,0);
		}
		public WhenClauseContext whenClause() {
			return getRuleContext(WhenClauseContext.class,0);
		}
		public LifetimeClauseContext lifetimeClause() {
			return getRuleContext(LifetimeClauseContext.class,0);
		}
		public CounterContext counter() {
			return getRuleContext(CounterContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public PeriodicityClauseContext periodicityClause() {
			return getRuleContext(PeriodicityClauseContext.class,0);
		}
		public SelectQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterSelectQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitSelectQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitSelectQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectQueryContext selectQuery() throws RecognitionException {
		SelectQueryContext _localctx = new SelectQueryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_selectQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				{
				setState(43);
				match(T__2);
				setState(44);
				retrieveSpecification();
				setState(45);
				match(T__3);
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(46);
					whereClause();
					}
				}

				setState(56);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EOF:
				case T__4:
				case T__20:
				case T__25:
					{
					{
					setState(50);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__20) {
						{
						setState(49);
						periodicityClause();
						}
					}

					setState(53);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__25) {
						{
						setState(52);
						lifetimeClause();
						}
					}

					}
					}
					break;
				case T__26:
					{
					setState(55);
					whenClause();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(58);
					match(T__4);
					}
				}

				}
				}
				break;
			case 2:
				{
				{
				setState(61);
				match(T__2);
				setState(62);
				aggregationSpecification();
				setState(63);
				match(T__3);
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(64);
					whereClause();
					}
				}

				setState(69);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(67);
					lifetimeClause();
					}
					break;
				case 2:
					{
					setState(68);
					counter();
					}
					break;
				}
				}
				}
				break;
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

	public static class RetrieveSpecificationContext extends ParserRuleContext {
		public SensorListContext sensorList() {
			return getRuleContext(SensorListContext.class,0);
		}
		public RetrieveSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_retrieveSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterRetrieveSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitRetrieveSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitRetrieveSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetrieveSpecificationContext retrieveSpecification() throws RecognitionException {
		RetrieveSpecificationContext _localctx = new RetrieveSpecificationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_retrieveSpecification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(73);
				match(T__5);
				}
				break;
			case IDENTIFIER:
				{
				setState(74);
				sensorList();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class AggregationSpecificationContext extends ParserRuleContext {
		public List<AggregationContext> aggregation() {
			return getRuleContexts(AggregationContext.class);
		}
		public AggregationContext aggregation(int i) {
			return getRuleContext(AggregationContext.class,i);
		}
		public List<SensorContext> sensor() {
			return getRuleContexts(SensorContext.class);
		}
		public SensorContext sensor(int i) {
			return getRuleContext(SensorContext.class,i);
		}
		public AggregationSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregationSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterAggregationSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitAggregationSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitAggregationSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregationSpecificationContext aggregationSpecification() throws RecognitionException {
		AggregationSpecificationContext _localctx = new AggregationSpecificationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_aggregationSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
			case T__10:
			case T__11:
			case T__12:
				{
				setState(77);
				aggregation();
				}
				break;
			case IDENTIFIER:
				{
				setState(78);
				sensor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(81);
				match(T__6);
				setState(84);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(82);
					sensor();
					}
					break;
				case T__9:
				case T__10:
				case T__11:
				case T__12:
					{
					setState(83);
					aggregation();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class AggregationContext extends ParserRuleContext {
		public AggregationTypeContext aggregationType() {
			return getRuleContext(AggregationTypeContext.class,0);
		}
		public SensorContext sensor() {
			return getRuleContext(SensorContext.class,0);
		}
		public AggregationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterAggregation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitAggregation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitAggregation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregationContext aggregation() throws RecognitionException {
		AggregationContext _localctx = new AggregationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_aggregation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			aggregationType();
			setState(92);
			match(T__7);
			setState(93);
			sensor();
			setState(94);
			match(T__8);
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

	public static class AggregationTypeContext extends ParserRuleContext {
		public AggregationTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregationType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterAggregationType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitAggregationType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitAggregationType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregationTypeContext aggregationType() throws RecognitionException {
		AggregationTypeContext _localctx = new AggregationTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_aggregationType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
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

	public static class SensorListContext extends ParserRuleContext {
		public List<SensorContext> sensor() {
			return getRuleContexts(SensorContext.class);
		}
		public SensorContext sensor(int i) {
			return getRuleContext(SensorContext.class,i);
		}
		public SensorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sensorList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterSensorList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitSensorList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitSensorList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SensorListContext sensorList() throws RecognitionException {
		SensorListContext _localctx = new SensorListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sensorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			sensor();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(99);
				match(T__6);
				{
				setState(100);
				sensor();
				}
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class WhereClauseContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__13);
			setState(107);
			expression();
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

	public static class ExpressionContext extends ParserRuleContext {
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			conditionalExpression();
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

	public static class ConditionalExpressionContext extends ParserRuleContext {
		public SensorContext sensor() {
			return getRuleContext(SensorContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public TerminalNode INT() { return getToken(MultiWearQueryLanguageParser.INT, 0); }
		public ConditionalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterConditionalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitConditionalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitConditionalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalExpressionContext conditionalExpression() throws RecognitionException {
		ConditionalExpressionContext _localctx = new ConditionalExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_conditionalExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			sensor();
			setState(112);
			operator();
			setState(113);
			match(INT);
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

	public static class OperatorContext extends ParserRuleContext {
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
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

	public static class PeriodicityClauseContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MultiWearQueryLanguageParser.INT, 0); }
		public PeriodicityClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_periodicityClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterPeriodicityClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitPeriodicityClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitPeriodicityClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PeriodicityClauseContext periodicityClause() throws RecognitionException {
		PeriodicityClauseContext _localctx = new PeriodicityClauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_periodicityClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__20);
			setState(118);
			match(INT);
			setState(119);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
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

	public static class LifetimeClauseContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MultiWearQueryLanguageParser.INT, 0); }
		public LifetimeClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lifetimeClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterLifetimeClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitLifetimeClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitLifetimeClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LifetimeClauseContext lifetimeClause() throws RecognitionException {
		LifetimeClauseContext _localctx = new LifetimeClauseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_lifetimeClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__25);
			setState(122);
			match(INT);
			setState(123);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
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

	public static class WhenClauseContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CounterContext counter() {
			return getRuleContext(CounterContext.class,0);
		}
		public LifetimeClauseContext lifetimeClause() {
			return getRuleContext(LifetimeClauseContext.class,0);
		}
		public PeriodicityClauseContext periodicityClause() {
			return getRuleContext(PeriodicityClauseContext.class,0);
		}
		public WhenClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterWhenClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitWhenClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitWhenClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenClauseContext whenClause() throws RecognitionException {
		WhenClauseContext _localctx = new WhenClauseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_whenClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__26);
			setState(126);
			expression();
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(135);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					{
					setState(128);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__20) {
						{
						setState(127);
						periodicityClause();
						}
					}

					setState(130);
					lifetimeClause();
					}
					}
					break;
				case 2:
					{
					{
					setState(131);
					lifetimeClause();
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__20) {
						{
						setState(132);
						periodicityClause();
						}
					}

					}
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(137);
				counter();
				}
				break;
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

	public static class CounterContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MultiWearQueryLanguageParser.INT, 0); }
		public CounterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_counter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterCounter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitCounter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitCounter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CounterContext counter() throws RecognitionException {
		CounterContext _localctx = new CounterContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_counter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(T__25);
			setState(141);
			match(INT);
			setState(142);
			match(T__27);
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

	public static class SensorContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MultiWearQueryLanguageParser.IDENTIFIER, 0); }
		public SensorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sensor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).enterSensor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiWearQueryLanguageListener ) ((MultiWearQueryLanguageListener)listener).exitSensor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MultiWearQueryLanguageVisitor ) return ((MultiWearQueryLanguageVisitor<? extends T>)visitor).visitSensor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SensorContext sensor() throws RecognitionException {
		SensorContext _localctx = new SensorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_sensor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u0095\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\5\2&\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4\62\n\4\3\4\5"+
		"\4\65\n\4\3\4\5\48\n\4\3\4\5\4;\n\4\3\4\5\4>\n\4\3\4\3\4\3\4\3\4\5\4D"+
		"\n\4\3\4\3\4\5\4H\n\4\5\4J\n\4\3\5\3\5\5\5N\n\5\3\6\3\6\5\6R\n\6\3\6\3"+
		"\6\3\6\5\6W\n\6\7\6Y\n\6\f\6\16\6\\\13\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\t\7\th\n\t\f\t\16\tk\13\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\5\20"+
		"\u0083\n\20\3\20\3\20\3\20\5\20\u0088\n\20\5\20\u008a\n\20\3\20\5\20\u008d"+
		"\n\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"\2\5\3\2\f\17\3\2\21\26\3\2\30\33\2\u0096\2%\3\2\2\2"+
		"\4)\3\2\2\2\6I\3\2\2\2\bM\3\2\2\2\nQ\3\2\2\2\f]\3\2\2\2\16b\3\2\2\2\20"+
		"d\3\2\2\2\22l\3\2\2\2\24o\3\2\2\2\26q\3\2\2\2\30u\3\2\2\2\32w\3\2\2\2"+
		"\34{\3\2\2\2\36\177\3\2\2\2 \u008e\3\2\2\2\"\u0092\3\2\2\2$&\5\4\3\2%"+
		"$\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\5\6\4\2(\3\3\2\2\2)*\7\3\2\2*+\7\37"+
		"\2\2+,\7\4\2\2,\5\3\2\2\2-.\7\5\2\2./\5\b\5\2/\61\7\6\2\2\60\62\5\22\n"+
		"\2\61\60\3\2\2\2\61\62\3\2\2\2\62:\3\2\2\2\63\65\5\32\16\2\64\63\3\2\2"+
		"\2\64\65\3\2\2\2\65\67\3\2\2\2\668\5\34\17\2\67\66\3\2\2\2\678\3\2\2\2"+
		"8;\3\2\2\29;\5\36\20\2:\64\3\2\2\2:9\3\2\2\2;=\3\2\2\2<>\7\7\2\2=<\3\2"+
		"\2\2=>\3\2\2\2>J\3\2\2\2?@\7\5\2\2@A\5\n\6\2AC\7\6\2\2BD\5\22\n\2CB\3"+
		"\2\2\2CD\3\2\2\2DG\3\2\2\2EH\5\34\17\2FH\5 \21\2GE\3\2\2\2GF\3\2\2\2H"+
		"J\3\2\2\2I-\3\2\2\2I?\3\2\2\2J\7\3\2\2\2KN\7\b\2\2LN\5\20\t\2MK\3\2\2"+
		"\2ML\3\2\2\2N\t\3\2\2\2OR\5\f\7\2PR\5\"\22\2QO\3\2\2\2QP\3\2\2\2RZ\3\2"+
		"\2\2SV\7\t\2\2TW\5\"\22\2UW\5\f\7\2VT\3\2\2\2VU\3\2\2\2WY\3\2\2\2XS\3"+
		"\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\13\3\2\2\2\\Z\3\2\2\2]^\5\16\b"+
		"\2^_\7\n\2\2_`\5\"\22\2`a\7\13\2\2a\r\3\2\2\2bc\t\2\2\2c\17\3\2\2\2di"+
		"\5\"\22\2ef\7\t\2\2fh\5\"\22\2ge\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2"+
		"j\21\3\2\2\2ki\3\2\2\2lm\7\20\2\2mn\5\24\13\2n\23\3\2\2\2op\5\26\f\2p"+
		"\25\3\2\2\2qr\5\"\22\2rs\5\30\r\2st\7 \2\2t\27\3\2\2\2uv\t\3\2\2v\31\3"+
		"\2\2\2wx\7\27\2\2xy\7 \2\2yz\t\4\2\2z\33\3\2\2\2{|\7\34\2\2|}\7 \2\2}"+
		"~\t\4\2\2~\35\3\2\2\2\177\u0080\7\35\2\2\u0080\u008c\5\24\13\2\u0081\u0083"+
		"\5\32\16\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2"+
		"\u0084\u008a\5\34\17\2\u0085\u0087\5\34\17\2\u0086\u0088\5\32\16\2\u0087"+
		"\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u0082\3\2"+
		"\2\2\u0089\u0085\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u008d\5 \21\2\u008c"+
		"\u0089\3\2\2\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\37\3\2\2"+
		"\2\u008e\u008f\7\34\2\2\u008f\u0090\7 \2\2\u0090\u0091\7\36\2\2\u0091"+
		"!\3\2\2\2\u0092\u0093\7\37\2\2\u0093#\3\2\2\2\24%\61\64\67:=CGIMQVZi\u0082"+
		"\u0087\u0089\u008c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}