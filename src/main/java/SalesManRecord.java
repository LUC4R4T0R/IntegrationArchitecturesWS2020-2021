import org.bson.Document;

public class SalesManRecord {
	
	private int salesmanId;
	private EvaluationRecord record;
	
	public SalesManRecord(int salesmanId, EvaluationRecord record) {
		this.salesmanId = salesmanId;
		this.record = record;
	}
	
	public int getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	public EvaluationRecord getRecord() {
		return record;
	}
	public void setRecord(EvaluationRecord record) {
		this.record = record;
	}
	
	public Document toDocument() {
    	Document d = new Document();
    	d.append("record", this.record);
		d.append("id", this.salesmanId);
    	return d;
    }
    
    public boolean equals(SalesMan s) {
    	return true;
    }
	
}
