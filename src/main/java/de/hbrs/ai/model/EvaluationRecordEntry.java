package de.hbrs.ai.model;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class that defines an evaluationrecordentry.
 */
public class EvaluationRecordEntry {

    /**
     * The target value;
     */
    private int expected;

    /**
     * The actual value;
     */
    private int actual;

    /**
     * The type of performance;
     */
    private String performance;

    /**
     * Constructor, that creates an evaluationrecordentry-Object with the given
     * values.
     */
    public EvaluationRecordEntry(int expectet, int actual, String performance) {
        this.expected = expectet;
        this.actual = actual;
        this.performance = performance;
    }

    /**
     * Default constructor;
     */
    public EvaluationRecordEntry(){

    }

    public String getPerformance() {
        return performance;
    }

    public int getActual() {
        return actual;
    }

    public int getExpected() {
        return expected;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public void setExpected(int expected) {
        this.expected = expected;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}
