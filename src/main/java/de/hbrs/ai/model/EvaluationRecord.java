package de.hbrs.ai.model;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class that defines an evaluationrecord.
 */
public class EvaluationRecord {

    /**
     * The evaluation for this record.
     * 0. Leadership Competence
     * 1. Openness to Employee
     * 2. Social Behavior to Employee
     * 3. Attitude towards Client
     * 4. Communication Skills
     * 5. Integrity to Company
     */
    private final int[] performance;

    /**
     * The year for this evaluationrecord;
     */
    private final int year;

    /**
     * Constructor, that creates an evaluationrecord-Object with the given
     * performance.
     */
    public EvaluationRecord(int[] performance, int year) {
        this.year = year;
        if (performance.length > 6) throw new IllegalArgumentException("Zu viele Performance-Einträge");
        this.performance = performance;
    }

    /**
     * Gets the evaluation of this evaluationrecord.
     *
     * @return Returns the performance-String.
     */
    public int[] getPerformance() {
        return performance;
    }

    /**
     * Gets the evaluation of this evaluationrecord.
     *
     * @return Returns the performance-String.
     */
    public int getYear() {
        return year;
    }

    /**
     * Compares an evaluationrecord with this one.
     *
     * @param er The evaluationrecord to compare with.
     * @return Returns true if the two evaluationrecords are equal and false if not.
     */
    public boolean equals(EvaluationRecord er) {
        return this.year == er.getYear() && this.performance[0] == er.getPerformance()[0] && this.performance[1] == er.getPerformance()[1] && this.performance[2] == er.getPerformance()[2] && this.performance[3] == er.getPerformance()[3] && this.performance[4] == er.getPerformance()[4] && this.performance[5] == er.getPerformance()[5];
    }
}
