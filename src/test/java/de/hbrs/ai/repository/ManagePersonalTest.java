package de.hbrs.ai.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ai.model.EvaluationRecord;
import de.hbrs.ai.model.EvaluationRecordEntry;
import de.hbrs.ai.model.SalesMan;
import de.hbrs.ai.model.SalesManRecord;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.junit.jupiter.api.Assertions.*;

class ManagePersonalTest {


    static ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
    static CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    static CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    static MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();
    static com.mongodb.client.MongoClient mongoClient = MongoClients.create(clientSettings);
    static MongoDatabase supermongo = mongoClient.getDatabase("test");
    static MongoCollection<SalesMan> salesmen = supermongo.getCollection("salesmen", SalesMan.class);
    static MongoCollection<SalesManRecord> records = supermongo.getCollection("records", SalesManRecord.class);


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
        salesmen = supermongo.getCollection("salesmen", SalesMan.class);
        records = supermongo.getCollection("records", SalesManRecord.class);

        s1 = new SalesMan("Jonas", "Brill", 1);
        s2 = new SalesMan("Luca", "Ringhausen", 2);
        s3 = new SalesMan("Luca", "Ringhausen", 3);
        salesmen.insertOne(s1);
        salesmen.insertOne(s2);
        salesmen.insertOne(s3);

        List<EvaluationRecordEntry> l1 = new ArrayList<>();
        l1.add(new EvaluationRecordEntry(4,4,"lc"));
        l1.add(new EvaluationRecordEntry(4,4,"ld"));


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
    static void delete(){
        //cleanup();
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
        assertNotNull(mp.readSalesMan(4));
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
        mp.updateSalesMen(s5);
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
        List<EvaluationRecordEntry> l1 = new ArrayList<>();
        l1.add(new EvaluationRecordEntry(4,4,"lc"));
        l1.add(new EvaluationRecordEntry(4,4,"ld"));

        EvaluationRecord r4 = new EvaluationRecord(l1, 2020);
        mp.addPerformanceRecord(r4, 3);
        assertNotNull(records.find(eq("salesmanId", 3)).first());
    }

    @Test
    public void getOneRecordTest(){
        SalesManRecord a = mp.getOneRecord(1,2020);
        boolean b = a.getPerformance().equals(e1);
        assertTrue(b);
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
        List<EvaluationRecordEntry> l1 = new ArrayList<>();
        l1.add(new EvaluationRecordEntry(4,4,"lc"));
        l1.add(new EvaluationRecordEntry(4,5,"ld"));
        EvaluationRecord r4 = new EvaluationRecord(l1, 2020);
        SalesManRecord s = new SalesManRecord(2,r4);

        mp.updateEvaluationRecord(s);
        List<EvaluationRecord> e = mp.readEvaluationRecords(2);
        boolean a = e.get(0).equals(r4);
        assertTrue(a);
    }

    @Test
    public void deleteEvaluationRecordTest() {
        mp.deleteEvaluationRecord(2, 2020);
        assertTrue(mp.readEvaluationRecords(2).isEmpty());
    }
}