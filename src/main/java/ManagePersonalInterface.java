package main.java;

import java.util.List;

public interface ManagePersonalInterface {

    public void createSalesMan( SalesMan record );

    public void addPerformanceReord( EvaluationRecord record , int sid );

    public SalesMan readSalesMan( int sid );

    public List<SalesMan> querySalesMan(String attribute , String key );

    public EvaluationRecord readEvaluationRecords( int sid );

}
