package de.hbrs.ia.model;

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
     * Constructor, that creates an evaluationrecord-Object with only the given
     * year.
     */
    public EvaluationRecord(int year) {
        this.year = year;
        this.performance = new ArrayList<>();
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
        boolean a;
        for (int i = 0; i < er.getPerformance().size(); i++) {
            a = er.getPerformance().get(i).getActual() == this.getPerformance().get(i).getActual() && er.getPerformance().get(i).getExpected() == this.getPerformance().get(i).getExpected() && er.getPerformance().get(i).getName().equals(this.getPerformance().get(i).getName());
            if (!a) {
                return false;
            }
        }
        return this.getYear() == er.getYear();
    }

    /*
                                                                            must exist cause of MongoDb automatic toJson
    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    public EvaluationRecord() {
        this.performance = new ArrayList<>();
    }

    public void setPerformance(List<EvaluationRecordEntry> performance) {
        this.performance = performance;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
