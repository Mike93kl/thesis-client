package QueryManager.queryprocessor.gen;// Generated from K:/Java/MultiWearANTLRv1.0\MultiWearQueryLanguage.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MultiWearQueryLanguageParser}.
 */
public interface MultiWearQueryLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(MultiWearQueryLanguageParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(MultiWearQueryLanguageParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#dataStorage}.
	 * @param ctx the parse tree
	 */
	void enterDataStorage(MultiWearQueryLanguageParser.DataStorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#dataStorage}.
	 * @param ctx the parse tree
	 */
	void exitDataStorage(MultiWearQueryLanguageParser.DataStorageContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#selectQuery}.
	 * @param ctx the parse tree
	 */
	void enterSelectQuery(MultiWearQueryLanguageParser.SelectQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#selectQuery}.
	 * @param ctx the parse tree
	 */
	void exitSelectQuery(MultiWearQueryLanguageParser.SelectQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#retrieveSpecification}.
	 * @param ctx the parse tree
	 */
	void enterRetrieveSpecification(MultiWearQueryLanguageParser.RetrieveSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#retrieveSpecification}.
	 * @param ctx the parse tree
	 */
	void exitRetrieveSpecification(MultiWearQueryLanguageParser.RetrieveSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#aggregationSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAggregationSpecification(MultiWearQueryLanguageParser.AggregationSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#aggregationSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAggregationSpecification(MultiWearQueryLanguageParser.AggregationSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#aggregation}.
	 * @param ctx the parse tree
	 */
	void enterAggregation(MultiWearQueryLanguageParser.AggregationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#aggregation}.
	 * @param ctx the parse tree
	 */
	void exitAggregation(MultiWearQueryLanguageParser.AggregationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#aggregationType}.
	 * @param ctx the parse tree
	 */
	void enterAggregationType(MultiWearQueryLanguageParser.AggregationTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#aggregationType}.
	 * @param ctx the parse tree
	 */
	void exitAggregationType(MultiWearQueryLanguageParser.AggregationTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#sensorList}.
	 * @param ctx the parse tree
	 */
	void enterSensorList(MultiWearQueryLanguageParser.SensorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#sensorList}.
	 * @param ctx the parse tree
	 */
	void exitSensorList(MultiWearQueryLanguageParser.SensorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(MultiWearQueryLanguageParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(MultiWearQueryLanguageParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MultiWearQueryLanguageParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MultiWearQueryLanguageParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(MultiWearQueryLanguageParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(MultiWearQueryLanguageParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(MultiWearQueryLanguageParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(MultiWearQueryLanguageParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#periodicityClause}.
	 * @param ctx the parse tree
	 */
	void enterPeriodicityClause(MultiWearQueryLanguageParser.PeriodicityClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#periodicityClause}.
	 * @param ctx the parse tree
	 */
	void exitPeriodicityClause(MultiWearQueryLanguageParser.PeriodicityClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#lifetimeClause}.
	 * @param ctx the parse tree
	 */
	void enterLifetimeClause(MultiWearQueryLanguageParser.LifetimeClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#lifetimeClause}.
	 * @param ctx the parse tree
	 */
	void exitLifetimeClause(MultiWearQueryLanguageParser.LifetimeClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void enterWhenClause(MultiWearQueryLanguageParser.WhenClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void exitWhenClause(MultiWearQueryLanguageParser.WhenClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#counter}.
	 * @param ctx the parse tree
	 */
	void enterCounter(MultiWearQueryLanguageParser.CounterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#counter}.
	 * @param ctx the parse tree
	 */
	void exitCounter(MultiWearQueryLanguageParser.CounterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiWearQueryLanguageParser#sensor}.
	 * @param ctx the parse tree
	 */
	void enterSensor(MultiWearQueryLanguageParser.SensorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiWearQueryLanguageParser#sensor}.
	 * @param ctx the parse tree
	 */
	void exitSensor(MultiWearQueryLanguageParser.SensorContext ctx);
}