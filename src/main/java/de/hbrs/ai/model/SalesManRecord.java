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
    private EvaluationRecord perfromance;

    /**
     * Constructor, that creates a salesman-Object with the given attributes.
     *
     * @param salesmanId  The id of the salesman, the record s about
     * @param perfromance The performance-record for the salesman.
     */
    public SalesManRecord(int salesmanId, EvaluationRecord perfromance) {
        this.salesmanId = salesmanId;
        this.perfromance = perfromance;
    }

    /**
     * Default constructor.
     */
    public SalesManRecord(){

    }

    public EvaluationRecord getPerfromance() {
        return perfromance;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public void setPerfromance(EvaluationRecord perfromance) {
        this.perfromance = perfromance;
    }

    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }
}

