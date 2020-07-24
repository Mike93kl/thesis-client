package QueryManager.queryprocessor.gen;// Generated from K:/Java/MultiWearANTLRv1.0\MultiWearQueryLanguage.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MultiWearQueryLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MultiWearQueryLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(MultiWearQueryLanguageParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#dataStorage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataStorage(MultiWearQueryLanguageParser.DataStorageContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#selectQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectQuery(MultiWearQueryLanguageParser.SelectQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#retrieveSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetrieveSpecification(MultiWearQueryLanguageParser.RetrieveSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#aggregationSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregationSpecification(MultiWearQueryLanguageParser.AggregationSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#aggregation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregation(MultiWearQueryLanguageParser.AggregationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#aggregationType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregationType(MultiWearQueryLanguageParser.AggregationTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#sensorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSensorList(MultiWearQueryLanguageParser.SensorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(MultiWearQueryLanguageParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MultiWearQueryLanguageParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(MultiWearQueryLanguageParser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(MultiWearQueryLanguageParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#periodicityClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPeriodicityClause(MultiWearQueryLanguageParser.PeriodicityClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#lifetimeClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLifetimeClause(MultiWearQueryLanguageParser.LifetimeClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#whenClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenClause(MultiWearQueryLanguageParser.WhenClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#counter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCounter(MultiWearQueryLanguageParser.CounterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MultiWearQueryLanguageParser#sensor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSensor(MultiWearQueryLanguageParser.SensorContext ctx);
}