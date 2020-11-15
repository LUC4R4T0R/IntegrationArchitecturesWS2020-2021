import org.bson.Document;

public class SalesManRecord {

	private int salesmanId;
	private EvaluationRecord perfromance;

	public SalesManRecord(int salesmanId, EvaluationRecord perfromance) {
		this.salesmanId = salesmanId;
		this.perfromance = perfromance;
	}

	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}

	public EvaluationRecord getPerfromance() {
		return perfromance;
	}

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
