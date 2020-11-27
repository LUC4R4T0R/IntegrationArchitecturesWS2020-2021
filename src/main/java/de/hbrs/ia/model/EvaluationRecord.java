package de.hbrs.ia.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class that defines an evaluationrecord.
 */
public class EvaluationRecord {

    /**
     * The list of performance-entries;
     */
    private List<EvaluationRecordEntry> performance;

    /**
     * The year for this evaluationrecord;
     */
    private int year;

    /**
     * Constructor, that creates an evaluationrecord-Object with the given
     * performance and year.
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
     * Default constructor
     */
    public EvaluationRecord() {
        this.performance = new ArrayList<>();
    }

    /**
     * Gets the list of performance-entries of this evaluationrecord.
     *
     * @return Returns the list of performance-entries.
     */
    public List<EvaluationRecordEntry> getPerformance() {
        return performance;
    }

    /**
     * Sets the list of performance-entries of this evaluationrecord.
     */
    public void setPerformance(List<EvaluationRecordEntry> performance) {
        this.performance = performance;
    }

    /**
     * Gets the year of this evaluationrecord.
     *
     * @return Returns the year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of this evaluationrecord.
     */
    public void setYear(int year) {
        this.year = year;
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
            a = er.getPerformance().get(i).getActual() == this.getPerformance().get(i).getActual()
                    && er.getPerformance().get(i).getTarget() == this.getPerformance().get(i).getTarget()
                    && er.getPerformance().get(i).getName().equals(this.getPerformance().get(i).getName());
            if (!a) {
                return false;
            }
        }
        return this.getYear() == er.getYear();
    }
}
