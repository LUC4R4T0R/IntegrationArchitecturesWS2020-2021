package de.hbrs.ai.model;

import org.bson.Document;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Helper-class to insert a record and its the salesman-id into the records-collection.
 */
public class SalesManRecord {

    /**
     * The id of the salesman, the record s about.
     */
    private int salesmanId;

    /**
     * The performance-record for the salesman.
     */
    private EvaluationRecord performance;

    /**
     * Constructor, that creates a salesman-Object with the given attributes.
     *
     * @param salesmanId  The id of the salesman, the record s about
     * @param performance The performance-record for the salesman.
     */
    public SalesManRecord(int salesmanId, EvaluationRecord performance) {
        this.salesmanId = salesmanId;
        this.performance = performance;
    }

    /**
     * Default constructor.
     */
    public SalesManRecord(){

    }

    public EvaluationRecord getPerformance() {
        return performance;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public void setPerformance(EvaluationRecord performance) {
        this.performance = performance;
    }

    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }
}

