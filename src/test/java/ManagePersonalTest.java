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
		
		records.insertOne(s1.toDocument());
	}

	@Test
	void createSalesManTest() {
		SalesMan s4 = new SalesMan("Jonas", "Brill", 4);
		mp.createSalesMan(s4);
		assertEquals(true, salesmen.find(eq("id", 4)).first() != null);	
	}

	@Test
	public void addPerformanceRecordTest() {

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

	@Test
	public void readEvaluationRecordsTest() {

	}
}