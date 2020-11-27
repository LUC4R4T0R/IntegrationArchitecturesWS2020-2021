package de.hbrs.ia.model;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class that defines an evaluationrecordentry.
 */
public class EvaluationRecordEntry {

    /**
     * The target value;
     */
    private int target;

    /**
     * The actual value;
     */
    private int actual;

    /**
     * The type of performance;
     */
    private String name;

    /**
     * Constructor, that creates an evaluationrecordentry-Object with the given
     * values.
     */
    public EvaluationRecordEntry(int expectet, int actual, String name) {
        this.target = expectet;
        this.actual = actual;
        this.name = name;
    }

    /**
     * Default constructor
     */
    public EvaluationRecordEntry() {
    }

    /**
     * Returns the name of this evaluationrecordentry.
     *
     * @return Returns the name of this evaluationrecordentry.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this evaluationrecordentry.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the actual-value of this evaluationrecordentry.
     *
     * @return Returns the actual-value of this evaluationrecordentry.
     */
    public int getActual() {
        return actual;
    }

    /**
     * Sets the actual-value of this evaluationrecordentry.
     */
    public void setActual(int actual) {
        this.actual = actual;
    }

    /**
     * Returns the target-value of this evaluationrecordentry.
     *
     * @return Returns the target-value of this evaluationrecordentry.
     */
    public int getTarget() {
        return target;
    }

    /**
     * Sets the target-value of this evaluationrecordentry.
     */
    public void setTarget(int expected) {
        this.target = expected;
    }

    /**
     * Compares this evaluationrecordentry to the given one.
     *
     * @param obj The evaluationrecordentry to compare with.
     * @return Returns true if the evaluationrecordentry are equal and false if not.
     */
    public boolean equals(Object obj) {
        if (obj instanceof EvaluationRecordEntry) {
            return this.name.equals(((EvaluationRecordEntry) obj).getName());
        }
        return super.equals(obj);
    }
}
