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
		d.append("performance", this.perfromance.getPerformance());
		d.append("id", this.salesmanId);
    	return d;
    }
    
    public boolean equals(SalesManRecord s) {
    	return true;
    }

}
