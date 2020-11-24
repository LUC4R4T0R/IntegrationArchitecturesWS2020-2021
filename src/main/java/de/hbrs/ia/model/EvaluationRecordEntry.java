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

    public String getPerformance() {
        return performance;
    }

    public int getActual() {
        return actual;
    }

    public int getExpected() {
        return expected;
    }


    /*
                                                                            must exist cause of MongoDb automatic toJson
    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    public EvaluationRecordEntry() {}

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
