package de.hbrs.ia.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class to test all evaluationrecord-methods.
 */
public class EvaluationRecordTest {

    //create two evaluationrecords to test
    EvaluationRecord e1 = new EvaluationRecord(new ArrayList<>(), 2020);
    EvaluationRecord e2 = new EvaluationRecord(new ArrayList<>(), 2021);

    @Test
    void constructorWithYearTest() {
        //creates new evaluationrecord-object with just a year
        EvaluationRecord e3 = new EvaluationRecord(2020);
        //test if object was createt
        assertNotNull(e3);
    }

    @Test
    void emptyConstructorTest() {
        //creates new empty evaluationrecord-object
        EvaluationRecord e4 = new EvaluationRecord();
        //test if object was createt
        assertNotNull(e4);
    }

    @Test
    void getPerformanceTest() {
        //test if the right entryList was returned
        assertEquals(e1.getPerformance(), new ArrayList<>());
    }

    @Test
    void setPerformanceTest() {
        //create entryobject-List
        List<EvaluationRecordEntry> e = new ArrayList<>();
        e.add(new EvaluationRecordEntry(10, 10, "test"));
        //set the entylist
        e2.setPerformance(e);
        //test if set worked
        assertEquals(e2.getPerformance(), e);
    }

    @Test
    void getYearTest() {
        //test if the right year was red
        assertEquals(2020, e1.getYear());
    }

    @Test
    void setYearTest() {
        //set the year for the secoudn evaluationrecord to 2020
        e2.setYear(2020);
        //test id the set worked
        assertEquals(2020, e2.getYear());
    }

    @Test
    void equalsTest() {
        //create filled evaluationrecord-objects
        List<EvaluationRecordEntry> e = new ArrayList<>();
        e.add(new EvaluationRecordEntry(10, 10, "test"));
        List<EvaluationRecordEntry> el = new ArrayList<>();
        el.add(new EvaluationRecordEntry(10, 11, "test"));
        EvaluationRecord e3 = new EvaluationRecord(e, 2020);
        EvaluationRecord e4 = new EvaluationRecord(e, 2020);
        EvaluationRecord e5 = new EvaluationRecord(el, 2020);

        //test if equals workes
        assertFalse(e1.equals(e2));
        assertTrue(e3.equals(e4));
        assertFalse(e3.equals(e5));
    }
}
