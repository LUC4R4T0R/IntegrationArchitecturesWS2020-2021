import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class ManagePersonal implements ManagePersonalInterface {
	
	private MongoCollection<Document> salesmen;
	
	public ManagePersonal(MongoCollection<Document> salesmen) {
		if (salesmen == null) {
			throw new IllegalArgumentException("Salesmen required");
		}
		this.salesmen = salesmen;
	}

	@Override
	public void createSalesMan(SalesMan record) {
		//Create the SalesMan document that can to be stored in the Database
		Document document = new Document();
		document.append("firstname", record.getFirstname());
		document.append("lastname", record.getLastname());
		document.append("id", record.getId());
		document.append("records", record.getRecords());

		//string the document in the Database
		salesmen.insertOne(document);
	}

	@Override
	//funktioniert wahrscheinlich nicht
	public void addPerformanceRecord(EvaluationRecord record, int sid) {
		Document d = salesmen.find(eq("id", ""+ sid)).first();
		List<EvaluationRecord> e = (List<EvaluationRecord>) d.get("record");
		e.add(record);
	}

	@Override
	public SalesMan readSalesMan(int sid) {
		MongoCollection<Document> test1 = salesmen;
		FindIterable<Document> test2 = test1.find(eq("id", sid));
		Document d = test2.first();
		return new SalesMan(d.getString("firstname"), d.getString("lastname"), d.getInteger("id"));
	}

	@Override
	public List<SalesMan> querySalesMan(String attribute, String key) {
		List<Document> d = salesmen.find(eq(key,attribute)).into(new ArrayList<Document>());
		List<SalesMan> s = new ArrayList<SalesMan>();
		for (int i = 0; i < d.size(); i++) {
			s.add(new SalesMan(d.get(i).getString("firstname"), d.get(i).getString("lastname"), d.get(i).getInteger("id"), (EvaluationRecord) d.get(i).get("records")));
		}
		return s;
	}

	@Override
	public EvaluationRecord readEvaluationRecords(int sid) {
		Document d = salesmen.find(eq("id", ""+ sid)).first();
		return (EvaluationRecord) d.get("records");
	}
}
