package QueryManager.queryprocessor;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

import QueryManager.queries.Aggregation;
import QueryManager.queries.Query;
import QueryManager.queryprocessor.gen.MultiWearQueryLanguageLexer;
import QueryManager.queryprocessor.gen.MultiWearQueryLanguageParser;
import QueryManager.response.QueryResult;

public class QueryProcessor {
    private static QueryProcessor _instance = new QueryProcessor();
    private QueryProcessor(){}
    public static QueryProcessor getInstance(){ return _instance;}
    public Query createQuery(String stringQuery) throws Exception{
        MultiWearQueryLanguageLexer lexer = new MultiWearQueryLanguageLexer(CharStreams.fromString(stringQuery));
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Pass the tokens to the parser
        MultiWearQueryLanguageParser parser = new MultiWearQueryLanguageParser(tokens);
        // Specify the query entry point
        MultiWearQueryLanguageParser.QueryContext queryContext = parser.query();

        QuerySpecifier specifier = new QuerySpecifier();

        MultiWearQueryLanguageParser.AggregationSpecificationContext aggregation =
                queryContext.selectQuery().aggregationSpecification();

        // if aggregation query
        if( aggregation != null ) {
            setAggregationQuery(aggregation,specifier);
            setTimeBasedData(specifier,queryContext);
            setMeasurementData(specifier,queryContext);
            return specifier.specify();
        }



        MultiWearQueryLanguageParser.RetrieveSpecificationContext retrieveSpecification =
                queryContext.selectQuery().retrieveSpecification();

        // set sensors
        MultiWearQueryLanguageParser.SensorListContext sensorListContext = retrieveSpecification.sensorList();
        for(MultiWearQueryLanguageParser.SensorContext sensorContext : sensorListContext.sensor()){
            specifier.getSelectedSensors().add(sensorContext.getText());
        }

        // if event based
        MultiWearQueryLanguageParser.WhenClauseContext whenClauseContext = queryContext.selectQuery().whenClause();
        if(whenClauseContext != null){
            specifier.setEventBased(true);
            setEventBasedQuery(specifier,whenClauseContext);
            if(whenClauseContext.counter() != null ) {
                specifier.setMeasurementNumber(Integer.parseInt(whenClauseContext.counter().INT().getText()));
            }else{
                if(whenClauseContext.lifetimeClause() != null){
                    specifier.setLifeTime(Integer.parseInt(whenClauseContext.lifetimeClause().INT().getText()));
                    specifier.setLifetimeTiming(whenClauseContext.lifetimeClause().stop.getText());
                }
                if(whenClauseContext.periodicityClause() != null) {
                    specifier.setPeriodicity(Integer.parseInt(whenClauseContext.periodicityClause().INT().getText()));
                    specifier.setPeriodicityTiming(whenClauseContext.periodicityClause().stop.getText());
                }
            }
            return specifier.specify();
        }

        // if periodic
        if(queryContext.selectQuery().lifetimeClause() != null) {
            specifier.setPeriodic(true);
            setTimeBasedData(specifier,queryContext);
            return specifier.specify();
        }

        return specifier.specify();
    }

    private void setEventBasedQuery(QuerySpecifier specifier, MultiWearQueryLanguageParser.WhenClauseContext whenClause) {
        String sensor = whenClause.expression().conditionalExpression().sensor().getText();
        String operator = whenClause.expression().conditionalExpression().operator().getText();
        float value = Float.parseFloat(whenClause.expression().conditionalExpression().INT().getText());
        specifier.addEventSpecifier(sensor,operator,value);

    }

    private void setTimeBasedData(QuerySpecifier specifier, MultiWearQueryLanguageParser.QueryContext queryContext){
        MultiWearQueryLanguageParser.LifetimeClauseContext lifetimeContext = queryContext.selectQuery().lifetimeClause();
        if( lifetimeContext != null ) {
            specifier.setLifeTime(Integer.parseInt(lifetimeContext.INT().getText()));
            specifier.setLifetimeTiming(lifetimeContext.stop.getText());
        }
        MultiWearQueryLanguageParser.PeriodicityClauseContext periodicityContext = queryContext.selectQuery().periodicityClause();
        if( periodicityContext != null ){
            specifier.setPeriodicity(Integer.parseInt(periodicityContext.INT().getText()));
            specifier.setPeriodicityTiming(periodicityContext.stop.getText());
        }
    }

    private void setMeasurementData(QuerySpecifier specifier, MultiWearQueryLanguageParser.QueryContext queryContext){
        MultiWearQueryLanguageParser.CounterContext counterContext = queryContext.selectQuery().counter();
        if(counterContext != null){
            specifier.setMeasurementNumber(Integer.parseInt(counterContext.INT().getText()));
            return;
        }
    }

    private void setAggregationQuery(MultiWearQueryLanguageParser.AggregationSpecificationContext aggregationContext, QuerySpecifier specifier) {
        specifier.setAggregation(true);
        for(MultiWearQueryLanguageParser.AggregationContext aggregation : aggregationContext.aggregation()){
            specifier.addAggregationSpecifier(aggregation.sensor().getText(),
                    aggregation.aggregationType().getText());
        }
        for(MultiWearQueryLanguageParser.SensorContext sensor : aggregationContext.sensor()){
            specifier.getSelectedSensors().add(sensor.getText());
        }

    }
}
