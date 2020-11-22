package de.hbrs.ai.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class that defines an evaluationrecord.
 */
public class EvaluationRecord {

    private List<EvaluationRecordEntry> performance;

    /**
     * The year for this evaluationrecord;
     */
    private int year;

    /**
     * Constructor, that creates an evaluationrecord-Object with the given
     * performance.
     */
    public EvaluationRecord(List<EvaluationRecordEntry> performance, int year) {
        this.year = year;
        this.performance = performance;
    }

    /**
     * Default constructor
     */
    public EvaluationRecord(){

    }

    /**
     * Gets the evaluation of this evaluationrecord.
     *
     * @return Returns the performance-String.
     */
    public List<EvaluationRecordEntry> getPerformance() {
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
        return this.getYear() == er.getYear();
    }

    public void setPerformance(List<EvaluationRecordEntry> performance) {
        this.performance = performance;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
