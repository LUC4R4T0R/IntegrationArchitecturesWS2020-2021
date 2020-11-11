import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

class ManagePersonalTest {

	private MongoClient client = new MongoClient("localhost", 27017);
	private MongoDatabase supermongo = client.getDatabase("highperformance");
	private MongoCollection<Document> salesmen = supermongo.getCollection("salesmen");
	private MongoCollection<Document> records = supermongo.getCollection("records");

	private ManagePersonal mp = new ManagePersonal(salesmen, records);
	
	private SalesMan s1;
	private SalesMan s2;
	private SalesMan s3;
	
	private SalesManRecord r1;
	private SalesManRecord r2;
	private SalesManRecord r3;
	

	@BeforeEach
	void setUp1() {
		if (salesmen != null) {
			salesmen.drop();
		}
		if (records != null) {
			records.drop();
		}
		salesmen = supermongo.getCollection("salesmen");
		records = supermongo.getCollection("records");


		s1 = new SalesMan("Jonas", "Brill", 1);
		s2 = new SalesMan("Luca", "Ringhausen", 2);
		s3 = new SalesMan("Luca", "Ringhausen", 3);
		salesmen.insertOne(s1.toDocument());
		salesmen.insertOne(s2.toDocument());
		salesmen.insertOne(s3.toDocument());
		
		
		EvaluationRecord e1 = new EvaluationRecord("sehr gute Arbeit");
		EvaluationRecord e2 = new EvaluationRecord("echt schelchte Arbeit");
		EvaluationRecord e3 = new EvaluationRecord("ausreichend gearbeitet, aber verbesserungs würdig");
		r1 = new SalesManRecord(1, e1);
		r2 = new SalesManRecord(1, e2);
		r3 = new SalesManRecord(2, e3);
		records.insertOne(r1.toDocument());
		records.insertOne(r2.toDocument());
		records.insertOne(r3.toDocument());

	}

	@Test
	void createSalesManTest() {
		SalesMan s4 = new SalesMan("Jonas", "Brill", 4);
		mp.createSalesMan(s4);
		assertEquals(true, salesmen.find(eq("id", 4)).first() != null);	
	}

	@Test
	public void addPerformanceRecordTest() {
		EvaluationRecord r4 = new EvaluationRecord("ganz okay");
		mp.addPerformanceRecord(r4, 3);
		assertEquals(true, records.find(eq("id", 3)).first() != null);	
	}

	@Test
	public void readSalesManTest() {
		SalesMan s = mp.readSalesMan(1);
		boolean a = s.equals(s1);
		assertEquals(true, a);
		
	}

	@Test
	public void querySalesManTest() {
		List<SalesMan> s = mp.querySalesMan("Luca", "firstname");
		boolean a = s.get(0).equals(s2);
		boolean b = s.get(1).equals(s3);
		assertEquals(true, a && b);
	}

//	@Test
//	public void readEvaluationRecordsTest() {
//		List<EvaluationRecord> e = mp.readEvaluationRecords(1);
//		boolean a = e.get(0).equals(r1.getPerfromance());
//		boolean b = e.get(1).equals(r2.getPerfromance());
//		assertEquals(true, a);
//	}
}