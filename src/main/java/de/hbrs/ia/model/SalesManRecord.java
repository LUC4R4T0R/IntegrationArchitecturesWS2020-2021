package de.hbrs.ia.model;

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
     * Constructor, that creates a salesmanrecord-object with the given attributes.
     *
     * @param salesmanId  The id of the salesman, the record is about
     * @param performance The performance-record for the salesman.
     */
    public SalesManRecord(int salesmanId, EvaluationRecord performance) {
        this.salesmanId = salesmanId;
        this.performance = performance;
    }

    /**
     * Default constructor
     */
    public SalesManRecord() {}

    /**
     * Returns the performance of this entry.
     *
     * @return Returns the performance.
     */
    public EvaluationRecord getPerformance() {
        return performance;
    }

    /**
     * Sets the performance of this evaluationentry.
     */
    public void setPerformance(EvaluationRecord performance) {
        this.performance = performance;
    }

    /**
     * Returns the id of this salesman's performance .
     *
     * @return Returns the id.
     */
    public int getSalesmanId() {
        return salesmanId;
    }

    /**
     * Sets the salesmanid. .
     */
    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }
}

