import org.bson.Document;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Helper-class to insert a record and its the SalesMan-id into the records-collection.
 */
public class SalesManRecord {

    /**
     * The id of the SalesMan, the record s about.
     */
    private final int salesmanId;

    /**
     * The performance-record for the SalesMan.
     */
    private final EvaluationRecord perfromance;

    /**
     * Constructor, that creates a SalesManRecord-Object with the given attributes.
     *
     * @param salesmanId  The id of the SalesMan, the record s about
     * @param perfromance The performance-record for the SalesMan.
     */
    public SalesManRecord(int salesmanId, EvaluationRecord perfromance) {
        this.salesmanId = salesmanId;
        this.perfromance = perfromance;
    }

    /**
     * This method takes this SalesManRecord and puts its attributes into a Document.
     *
     * @return Returns the Document witch is build out of this SalesManRecords attributes.
     */
    public Document toDocument() {
        Document d = new Document();
        d.append("LC", this.perfromance.getPerformance()[0]);
        d.append("OtE", this.perfromance.getPerformance()[1]);
        d.append("SBtE", this.perfromance.getPerformance()[2]);
        d.append("AtC", this.perfromance.getPerformance()[3]);
        d.append("CS", this.perfromance.getPerformance()[4]);
        d.append("ItC", this.perfromance.getPerformance()[5]);
        d.append("id", this.salesmanId);
        int year = this.perfromance.getYear();
        d.append("year", year);
        return d;
    }

}
