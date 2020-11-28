package de.hbrs.ia.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class to test all evaluationrecordentry-methods.
 */
public class EvaluationRecordEntryTest {

    //create two evaluationrecordentry-objects to test
    EvaluationRecordEntry er1 = new EvaluationRecordEntry(10, 10, "test");
    EvaluationRecordEntry er2 = new EvaluationRecordEntry(10, 10, "test1");

    @Test
    void emptyConstructorTest() {
        //create new empty evaluationrecordentry
        EvaluationRecordEntry er3 = new EvaluationRecordEntry();
        //test if an evaluationrecordentry was created
        assertNotNull(er3);
    }

    @Test
    void getNameTest() {
        //test if the right name is red
        assertEquals("test", er1.getName());
    }

    @Test
    void setNameTest() {
        //set the name of the secound evaluationrecordentry
        er2.setName("Jonas");
        //test if set worked
        assertEquals("Jonas", er2.getName());
    }

    @Test
    void getActualTest() {
        //test if the right actual-value is red
        assertEquals(10, er1.getActual());
    }

    @Test
    void setActualTest() {
        //set the actual-value of the secound evaluationrecordentry
        er2.setActual(11);
        //test if set worked
        assertEquals(11, er2.getActual());
    }

    @Test
    void getTargetTest() {
        //test if the right target-value is red
        assertEquals(10, er1.getTarget());
    }

    @Test
    void setTargetTest() {
        //set the target-value of the secound evaluationrecordentry
        er2.setTarget(11);
        //test if set worked
        assertEquals(11, er2.getTarget());
    }

    @Test
    void equalsTest() {
        //create an evaluationrecordentry
        EvaluationRecordEntry er5 = new EvaluationRecordEntry(10, 10, "test");
        //test if equals-method works
        assertEquals(er5, er1);
    }
}
