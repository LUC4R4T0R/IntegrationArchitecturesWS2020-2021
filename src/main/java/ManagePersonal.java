import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

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
		salesmen.insertOne(new SalesMan(record.getFirstname(), record.getLastname(), record.getId()).toDocument());
	}

	@Override
	public void addPerformanceRecord(EvaluationRecord record, int sid) {
		Document d = salesmen.find(eq("id", ""+ sid)).first();
		List<EvaluationRecord> e = (List<EvaluationRecord>) d.get("record");
		e.add(record);
	}

	@Override
	public SalesMan readSalesMan(int sid) {
		Document d = salesmen.find(eq("id", sid)).first();
		return new SalesMan(d.getString("firstname"), d.getString("lastname"), d.getInteger("id"));
	}

	@Override
	public List<SalesMan> querySalesMan(String attribute, String key) {
		List<Document> d = salesmen.find(eq(key,attribute)).into(new ArrayList<Document>());
		List<SalesMan> s = new ArrayList<SalesMan>();
		for (int i = 0; i < d.size(); i++) {
			s.add(new SalesMan(d.get(i).getString("firstname"), d.get(i).getString("lastname"), d.get(i).getInteger("id")));
		}
		return s;
	}

	@Override
	public EvaluationRecord readEvaluationRecords(int sid) {
		Document d = salesmen.find(eq("id", ""+ sid)).first();
		return (EvaluationRecord) d.get("records");
	}
}
