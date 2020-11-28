package de.hbrs.ia.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ia.model.EvaluationRecord;
import de.hbrs.ia.model.EvaluationRecordEntry;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SalesManRecord;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test all managepersonal-methods.
 */
class ManagePersonalTest {

    //setup Database connection and the test database and collections
    static ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
    static CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    static CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    static MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();
    static com.mongodb.client.MongoClient mongoClient = MongoClients.create(clientSettings);
    static MongoDatabase supermongo = mongoClient.getDatabase("test");
    static MongoCollection<SalesMan> salesmen = supermongo.getCollection("salesmen", SalesMan.class);
    static MongoCollection<SalesManRecord> records = supermongo.getCollection("records", SalesManRecord.class);

    /**
     * The managepersonal-objekt that will be testet.
     */
    private final ManagePersonal mp = new ManagePersonal(salesmen, records);

    /**
     * The three salesmen that are inserted into the collection before each test runs.
     */
    private SalesMan s1;
    private SalesMan s2;
    private SalesMan s3;

    /**
     * The three evaluationrecords that are inserted into the collection before each test runs.
     */
    EvaluationRecord e1;
    EvaluationRecord e2;
    EvaluationRecord e3;

    static void cleanup() {
        salesmen.drop();
        records.drop();
    }

    @BeforeEach
    void setUp() {
        //reset the database before each test
        cleanup();
        salesmen = supermongo.getCollection("salesmen", SalesMan.class);
        records = supermongo.getCollection("records", SalesManRecord.class);

        //insert salesman-data
        s1 = new SalesMan("Jonas", "Brill", 1);
        s2 = new SalesMan("Luca", "Ringhausen", 2);
        s3 = new SalesMan("Luca", "Ringhausen", 3);
        salesmen.insertOne(s1);
        salesmen.insertOne(s2);
        salesmen.insertOne(s3);

        //insert record-data
        List<EvaluationRecordEntry> l1 = new ArrayList<>();
        l1.add(new EvaluationRecordEntry(4, 4, "lc"));
        l1.add(new EvaluationRecordEntry(4, 4, "ld"));
        e1 = new EvaluationRecord(l1, 2020);
        e2 = new EvaluationRecord(l1, 2021);
        e3 = new EvaluationRecord(l1, 2020);
        SalesManRecord r1 = new SalesManRecord(1, e1);
        SalesManRecord r2 = new SalesManRecord(1, e2);
        SalesManRecord r3 = new SalesManRecord(2, e3);
        records.insertOne(r1);
        records.insertOne(r2);
        records.insertOne(r3);

    }

    @AfterAll
    static void clean() {
        cleanup();
    }

    /*                                                                     start of salesman-tests
   ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    */
    @Test
    void createSalesManTest() {
        //create a new salesman
        SalesMan s4 = new SalesMan("Jonas", "Brill", 4);
        //insert him into the database
        mp.createSalesMan(s4);

        //test if salesman was inserted correctly
        assertNotNull(mp.readSalesMan(4));
        //test if exception is thrown if we try to insert the same salesman again
        assertThrows(IllegalArgumentException.class, () -> mp.createSalesMan(s4));
    }

    @Test
    public void readSalesManTest() {
        //get the salesman with the id 1 out of the database
        SalesMan s = mp.readSalesMan(1);
        //check if it is the right salesman
        boolean a = s.equals(s1);

        //test if the right salesman was red
        assertTrue(a);
        //test if exception is thrown if we try to read a non existing salesman
        assertThrows(NoSuchElementException.class, () -> mp.readSalesMan(10));

    }

    @Test
    public void querySalesManTest() {
        //get all salesmen with the key attribute pair "firstname" "Luca" out of the database
        List<SalesMan> s = mp.querySalesMan("Luca", "firstname");
        //check if the right salesmen where red
        boolean a = s.get(0).equals(s2);
        boolean b = s.get(1).equals(s3);

        //test if the right salesmen where red
        assertTrue(a && b);
        //test if exception is thrown if we try to read a salesmen when no salesman has this key attribute pair
        assertThrows(NoSuchElementException.class, () -> mp.querySalesMan("test", "firstname"));
    }

    @Test
    public void getAllSaleMenTest() {
        //get all salesmen
        List<SalesMan> s = mp.getAllSalesMen();
        //check if the right salesmen where red
        boolean a = s.get(0).equals(s1);
        boolean b = s.get(1).equals(s2);
        boolean c = s.get(2).equals(s3);

        //test if the right salesmen where red
        assertTrue(a && b && c);
    }

    @Test
    public void updateSalesMenTest() {
        //create a new salesman
        SalesMan s5 = new SalesMan("test", "Brill", 1);
        //create a new salesman
        SalesMan s6 = new SalesMan("test", "Brill", 10);
        //update the salesman with th id 1 with the new salesman
        mp.updateSalesMen(s5);
        //get the salesman with the id 1 and chack if he is updated
        SalesMan s = mp.readSalesMan(1);
        boolean a = s.equals(s5);

        //test if the update worked
        assertTrue(a);
        //test if exception is thrown if we try to update a non existing salesman
        assertThrows(NoSuchElementException.class, () -> mp.updateSalesMen(s6));
    }

    @Test
    public void deleteSalesMenTest() {
        //delete the salesman with th id 1
        mp.deleteSalesMen(1);

        //test if delete worked
        assertThrows(NoSuchElementException.class, () -> mp.readSalesMan(1));
        //test if exception is thrown if we try to delete a non existing salesman
        assertThrows(NoSuchElementException.class, () -> mp.deleteSalesMen(10));
        //test if the evaluationrecord of this salesman where deleted
        assertThrows(NoSuchElementException.class, () -> mp.readEvaluationRecords(1));
    }


    /*                                                                     start of salesman-tests
   ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    */


    @Test
    public void addPerformanceRecordTest() {
        //create new evaluationrecord
        List<EvaluationRecordEntry> l1 = new ArrayList<>();
        l1.add(new EvaluationRecordEntry(4, 4, "lc"));
        l1.add(new EvaluationRecordEntry(4, 4, "ld"));
        EvaluationRecord r4 = new EvaluationRecord(l1, 2020);
        //insert into the database
        mp.addPerformanceRecord(r4, 3);

        //test if create worked
        assertNotNull(records.find(eq("salesmanId", 3)).first());
        //test if exception is thrown if we try to insert the same evaluationrecord again
        assertThrows(IllegalArgumentException.class, () -> mp.addPerformanceRecord(r4,3));
    }

    @Test
    public void getOneRecordTest() {
        //get the evaluationrecord
        EvaluationRecord a = mp.getOneRecord(1, 2020);

        //test if the right evaluationrecord was red
        assertTrue(a.equals(e1));
        //test if null is returned if we try to read an evaluationrecord that does not exist
        assertNull(mp.getOneRecord(10,2021));
    }

    @Test
    public void readEvaluationRecordsTest() {
        //get all evaluationrecords with the given attribute
        List<EvaluationRecord> e = mp.readEvaluationRecords(1);
        //check if they are the right one
        boolean a = e.get(0).equals(e1);
        boolean b = e.get(1).equals(e2);

        //test if the right evaluationrecords where red
        assertTrue(a && b);
    }

    @Test
    public void updateEvaluationRecordTest() {
        //create new evaluationrecord
        List<EvaluationRecordEntry> l1 = new ArrayList<>();
        l1.add(new EvaluationRecordEntry(4, 4, "lc"));
        l1.add(new EvaluationRecordEntry(4, 5, "ld"));
        EvaluationRecord r4 = new EvaluationRecord(l1, 2020);
        SalesManRecord s = new SalesManRecord(2, r4);
        //update the evaluationrecord with the id 2 and year 2020
        mp.updateEvaluationRecord(s);
        //try to read the updated evaluationrecord
        List<EvaluationRecord> e = mp.readEvaluationRecords(2);
        //check if the evaluationrecord was updated
        boolean a = e.get(0).equals(r4);

        //test if the update worked
        assertTrue(a);
        //test if exception is thrown if we try to update a non existing evaluationrecord
        assertThrows(NoSuchElementException.class, () -> mp.updateEvaluationRecord(new SalesManRecord(10,r4)));
    }

    @Test
    public void deleteEvaluationRecordTest() {
        //delete the evaluationrecord with the id 2
        mp.deleteEvaluationRecord(2, 2020);

        //test if the evaluationrecord was deleted
        assertThrows(NoSuchElementException.class, () -> mp.readEvaluationRecords(2));
        //test if exception is thrown if we try to delete a non existing evaluationrecord
        assertThrows(NoSuchElementException.class, () -> mp.deleteEvaluationRecord(10, 2020));
    }

    /*                                                                     start of evaluationenrecordentry-tests
   ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    */

    @Test
    public void addRecordEntryTest(){
        //create new evaluationrecordentry
        EvaluationRecordEntry ere = new EvaluationRecordEntry(10,10,"name");
        //insert it into the dataabase
        mp.addRecordEntry(1,2020,ere);

        mp.createSalesMan(new SalesMan("Jonas","Brill",4));

        //test if create worked
        assertNotNull(mp.getOneEntry(1,2020,"name"));
        //test if exception is thrown if we try to create an entry for an non existing evaluationrecord
        assertThrows(IllegalArgumentException.class, () -> mp.addRecordEntry(4,2020,ere));
    }

    @Test
    public void getAllEntrysTest(){
        //try to get all evaluationrecordentries with this is and year
        List<EvaluationRecordEntry> le = mp.getAllEntrys(1,2020);

        //test if the right entries where red
        assertTrue(le.get(0).equals(e1.getPerformance().get(0)) && le.get(1).equals(e1.getPerformance().get(1)));
        //test if exception is thrown if we try to read non existing entries
        assertThrows(NoSuchElementException.class, () -> mp.getAllEntrys(10,2020));
    }

    @Test
    public void getOneEntryTest(){
        //try to read the evaluationrecordentry with the id 2, year 2020 and name "ld"
        EvaluationRecordEntry ere = mp.getOneEntry(2,2020,"ld");

        //test if the right evaluationrecordentry was red
        assertEquals(e3.getPerformance().get(1), ere);
        //test if exception is thrown if we try to read a non existing evaluationrecordentry
        assertThrows(NoSuchElementException.class,() -> mp.getOneEntry(10,2020,"ld"));
        assertThrows(NoSuchElementException.class,() -> mp.getOneEntry(2,2020,"le"));
    }

    @Test
    public void updateEntryTest(){
        //create new evaluationrecordentries
        EvaluationRecordEntry ere = new EvaluationRecordEntry(10,10,"ld");
        EvaluationRecordEntry ere1 = new EvaluationRecordEntry(10,10,"le");
        //update the entry
        mp.updateEntry(2,2020, ere);

        //test if update worked
        assertEquals(ere, e3.getPerformance().get(1));
        //test if exception is thrown if we try to update a non existing entry
        assertThrows(NoSuchElementException.class, () -> mp.updateEntry(2,2020, ere1));
        assertThrows(NoSuchElementException.class, () -> mp.updateEntry(4,2020, ere));

    }

    @Test
    public void deleteEntryTest(){
        //delete the evaluationrecordenty with the id 2 and year 2020 and name "ld"
        mp.deleteEntry(2, 2020, "ld");

        //test if the evaluationrecord was deleted
        assertThrows(NoSuchElementException.class, () -> mp.getOneEntry(2,2020,"ld"));
        //test if exception is thrown if we try to delete a non existing evaluationrecord
        assertThrows(NoSuchElementException.class, () -> mp.deleteEntry(2, 2020, "le"));
        assertThrows(NoSuchElementException.class, () -> mp.deleteEntry(4, 2020, "ld"));
    }
}