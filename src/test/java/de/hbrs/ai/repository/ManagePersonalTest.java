package de.hbrs.ai.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ai.model.EvaluationRecord;
import de.hbrs.ai.model.SalesMan;
import de.hbrs.ai.model.SalesManRecord;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.*;

class ManagePersonalTest {

    private static final MongoClient client = new MongoClient("localhost", 27017);
    private static final MongoDatabase supermongo = client.getDatabase("TestDatabase");
    private static MongoCollection<Document> salesmen = supermongo.getCollection("salesmen");
    private static MongoCollection<Document> records = supermongo.getCollection("records");

    private final ManagePersonal mp = new ManagePersonal(salesmen, records);

    private SalesMan s1;
    private SalesMan s2;
    private SalesMan s3;

    EvaluationRecord e1;
    EvaluationRecord e2;
    EvaluationRecord e3;


    @BeforeEach
    void setUp1() {
        cleanup();
        salesmen = supermongo.getCollection("salesmen");
        records = supermongo.getCollection("records");

        s1 = new SalesMan("Jonas", "Brill", 1);
        s2 = new SalesMan("Luca", "Ringhausen", 2);
        s3 = new SalesMan("Luca", "Ringhausen", 3);
        salesmen.insertOne(s1.toDocument());
        salesmen.insertOne(s2.toDocument());
        salesmen.insertOne(s3.toDocument());


        int[] array1 = {1, 1, 1, 1, 1, 1};
        int[] array2 = {2, 2, 2, 2, 2, 2};
        int[] array3 = {3, 3, 3, 3, 3, 3};
        e1 = new EvaluationRecord(array1, 2020);
        e2 = new EvaluationRecord(array2, 2021);
        e3 = new EvaluationRecord(array3, 2020);
        SalesManRecord r1 = new SalesManRecord(1, e1);
        SalesManRecord r2 = new SalesManRecord(1, e2);
        SalesManRecord r3 = new SalesManRecord(2, e3);
        records.insertOne(r1.toDocument());
        records.insertOne(r2.toDocument());
        records.insertOne(r3.toDocument());

    }

    @AfterTestClass
    void delete(){
        cleanup();
    }

    static void cleanup(){
        salesmen.drop();
        records.drop();
    }


    /*
                                                                          start of Test
    ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    @Test
    void createSalesManTest() {
        SalesMan s4 = new SalesMan("Jonas", "Brill", 4);
        mp.createSalesMan(s4);
        assertNotNull(salesmen.find(eq("id", 4)).first());
    }

    @Test
    public void readSalesManTest() {
        SalesMan s = mp.readSalesMan(1);
        boolean a = s.equals(s1);
        assertTrue(a);

    }

    @Test
    public void querySalesManTest() {
        List<SalesMan> s = mp.querySalesMan("Luca", "firstname");
        boolean a = s.get(0).equals(s2);
        boolean b = s.get(1).equals(s3);
        assertTrue(a && b);
    }

    @Test
    public void getAllSaleMenTest() {
        List<SalesMan> s = mp.getAllSalesMen();
        boolean a = s.get(0).equals(s1);
        boolean b = s.get(1).equals(s2);
        boolean c = s.get(2).equals(s3);
        assertTrue(a && b && c);
    }

    @Test
    public void updateSalesMenTest() {
        SalesMan s5 = new SalesMan("test", "Brill", 1);
        mp.updateSalesMen(1, "test", "Brill");
        SalesMan s = mp.readSalesMan(1);
        boolean a = s.equals(s5);
        assertTrue(a);
    }

    @Test
    public void deleteSalesMenTest() {
        mp.deleteSalesMen(1);
        assertNull(mp.readSalesMan(1));
    }

    @Test
    public void addPerformanceRecordTest() {
        int[] a = {1, 1, 1, 1, 1, 1};
        EvaluationRecord r4 = new EvaluationRecord(a, 2020);
        mp.addPerformanceRecord(r4, 3);
        assertNotNull(records.find(eq("id", 3)).first());
    }

    @Test
    public void readEvaluationRecordsTest() {
        List<EvaluationRecord> e = mp.readEvaluationRecords(1);
        boolean a = e.get(0).equals(e1);
        boolean b = e.get(1).equals(e2);
        assertTrue(a && b);
    }

    @Test
    public void updateEvaluationRecordTest() {
        int[] test = {10, 3, 3, 3, 3, 3};
        EvaluationRecord e4 = new EvaluationRecord(test, 2020);
        mp.updateEvaluationRecord(2, 2020, "LC", 10);
        List<EvaluationRecord> e = mp.readEvaluationRecords(2);
        boolean a = e.get(0).equals(e4);
        assertTrue(a);
    }

    @Test
    public void deleteEvaluationRecordTest() {
        mp.deleteEvaluationRecord(2, 2020);
        assertTrue(mp.readEvaluationRecords(2).isEmpty());
    }
}