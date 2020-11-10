import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

class ManagePersonalTest {

	private MongoClient client = new MongoClient("localhost", 27017);
	private MongoDatabase supermongo = client.getDatabase("highperformance");
	private MongoCollection<Document> salesmen;

	private ManagePersonal mp = new ManagePersonal(salesmen);
	
	private SalesMan s1;
	private SalesMan s2;
	private SalesMan s3;
	

	@BeforeEach
	void setUp1() {
		if (salesmen != null) {
			salesmen.drop();
		}
		salesmen = supermongo.getCollection("salesmen");
		
		Document document1 = new Document();
		document1.append("firstname", "Jonas");
		document1.append("lastname", "Brill");
		document1.append("id", 1);
		document1.append("records", null);
		s1 = new SalesMan("Jonas", "Brill", 1, null);

//		Document document2 = new Document();
//		document2.append("firstname", "Luca");
//		document2.append("lastname", "Ringhause");
//		document2.append("id", "2");
//		document2.append("records", null);
//		s2 = new SalesMan("Luca", "Ringhausen", 2, null);
//
//		Document document3 = new Document();
//		document3.append("firstname", "Luca");
//		document3.append("lastname", "Ringhausen");
//		document3.append("id", "3");
//		document3.append("records", null);
//		s3 = new SalesMan("Luca", "Ringhausen", 3, null);

		salesmen.insertOne(document1);
//		salesmen.insertOne(document2);
//		salesmen.insertOne(document3);
	}

//	@Test
//	void createSalesManTest() {
//
//	}
//
//	@Test
//	public void addPerformanceRecordTest() {
//
//	}

	@Test
	public void readSalesManTest() {
		SalesMan s = mp.readSalesMan(1);
		assertEquals(s1, s);
		
	}

//	@Test
//	public void querySalesManTest() {
//
//	}
//
//	@Test
//	public void readEvaluationRecordsTest() {
//
//	}
}