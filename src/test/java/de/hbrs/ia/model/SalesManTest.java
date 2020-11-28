package de.hbrs.ia.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test all salesman-methods.
 */
public class SalesManTest {

    //creat two test salesmen
    SalesMan testSalesman = new SalesMan("Jonas", "Brill", 1);
    SalesMan testSalesman1 = new SalesMan("Jonas", "Brill", 1);

    @Test
    void emptyconstructorTest(){
        SalesMan s = new SalesMan();
        assertNotNull(s);
    }

    @Test
    void getIdTest() {
        //test if right id is returned
        assertEquals(1, testSalesman.getId());
    }

    @Test
    void setId() {
        //set the id
        testSalesman1.setId(2);

        //test if set worked
        assertEquals(2, testSalesman1.getId());
    }

    @Test
    void getFirstnameTest() {
        //test if right firstname is returned
        assertEquals("Jonas", testSalesman.getFirstname());
    }

    @Test
    void setFirstnameTest() {
        //set the firstname
        testSalesman1.setFirstname("Luca");

        //test if set worked
        assertEquals("Luca", testSalesman1.getFirstname());
    }

    @Test
    void getLastnameTest() {
        //test if right lastname is returned
        assertEquals("Brill", testSalesman.getLastname());
    }

    @Test
    void setLastnameTest() {
        //set the lastname
        testSalesman1.setLastname("Ringhausen");

        //test if set worked
        assertEquals("Ringhausen", testSalesman1.getLastname());
    }

    @Test
    void toStringTest() {
        //test if right String is returned
        assertEquals("1: Jonas Brill", testSalesman.toString());
    }

    @Test
    void equalsTest() {
        //create new salesman with the same attributes as the first one
        SalesMan testSalesman2 = new SalesMan("Jonas", "Brill", 1);

        //test if equals-method works
        assertTrue(testSalesman2.equals(testSalesman));
    }
}
