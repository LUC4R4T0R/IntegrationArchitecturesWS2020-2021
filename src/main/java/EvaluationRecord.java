public class EvaluationRecord {
	
	private String performance;
	
	public EvaluationRecord(String performance) {
		this.performance = performance;
	}
	
	public String getPerformance() {
		return performance;
	}
	
    public boolean equals(EvaluationRecord s) {
    	return this.performance.equals(s.getPerformance());
    }
}
