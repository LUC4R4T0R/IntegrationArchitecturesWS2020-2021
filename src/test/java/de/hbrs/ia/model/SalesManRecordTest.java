package de.hbrs.ia.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class to test all salesmanrecord-methods.
 */
public class SalesManRecordTest {

    //create new salesmanrecord
    SalesManRecord sr = new SalesManRecord(1, new EvaluationRecord(new ArrayList<>(), 2020));
    SalesManRecord sr1 = new SalesManRecord(2, new EvaluationRecord(new ArrayList<>(), 2021));

    @Test
    void emptyConstructorTest() {
        //creates new empty salesmanrecord-object
        SalesManRecord sr2 = new SalesManRecord();
        //test if object was createt
        assertNotNull(sr2);
    }

    @Test
    void getPerformanceTest() {
        //test if the right evaluationrecord is returned
        assertTrue(new EvaluationRecord(new ArrayList<>(), 2020).equals(sr.getPerformance()));
    }

    @Test
    void setPerformanceTest() {
        //set the evaluationrecord of the salesmanrecord
        sr1.setPerformance(new EvaluationRecord(new ArrayList<>(), 2030));
        //test if the evaluationrecord was set correctly
        assertTrue(new EvaluationRecord(new ArrayList<>(), 2030).equals(sr1.getPerformance()));
    }

    @Test
    void getSalesmanIdTest() {
        //test if the right id is returned
        assertEquals(1, sr.getSalesmanId());
    }

    @Test
    void setSalesmanIdTest() {
        //set the salesmanid of the salesmanrecord
        sr1.setSalesmanId(2);
        //test if the salesmanid was set correctly
        assertEquals(2, sr1.getSalesmanId());
    }
}
