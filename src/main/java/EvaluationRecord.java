/**
 * 
 * @author jbrill2s, lringh2s
 * 
 *         Class that defines an EvaluationRecord.
 * 
 */
public class EvaluationRecord {

	/**
	 * The evaluation for this record.
	 */
	private String performance;

	/**
	 * Constructor, that creates an EvaluationRecord-Object with the given
	 * performance.
	 */
	public EvaluationRecord(String performance) {
		this.performance = performance;
	}

	/**
	 * Gets the evaluation of this EvaluationRecord.
	 * 
	 * @return Returns the performance-String.
	 */
	public String getPerformance() {
		return performance;
	}

	/**
	 * Compares an EvaluationRecord with this one.
	 * 
	 * @param er The EvaluationRecord to compare with.
	 * 
	 * @return Returns true if the two EvaluationRecords are equal and false if not.
	 */
	public boolean equals(EvaluationRecord er) {
		return this.performance.equals(er.getPerformance());
	}
}
