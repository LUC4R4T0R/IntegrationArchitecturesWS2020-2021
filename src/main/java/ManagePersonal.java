import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class ManagePersonal implements ManagePersonalInterface {
	
	private MongoCollection<Document> salesmen;
	private MongoCollection<Document> records;
	
	public ManagePersonal(MongoCollection<Document> salesmen, MongoCollection<Document> record) {
		if (salesmen == null) {
			throw new IllegalArgumentException("Salesmen required");
		}
		this.salesmen = salesmen;
		if (record == null) {
			throw new IllegalArgumentException("Record required");
		}
		this.records = record;
	}

	
	// einen Salesman der Datenbank hinzufügen
	@Override
	public void createSalesMan(SalesMan record) {
		salesmen.insertOne(new SalesMan(record.getFirstname(), record.getLastname(), record.getId()).toDocument());
	}

	//einen Performance Record der Datenbank hinzufügen
	@Override
	public void addPerformanceRecord(EvaluationRecord record, int sid) {
		records.insertOne(new SalesManRecord(sid, record).toDocument());
	}

	//einen Salesman mithilfe der Id finden und auslesen
	@Override
	public SalesMan readSalesMan(int sid) {
		Document d = salesmen.find(eq("id", sid)).first();
		return new SalesMan(d.getString("firstname"), d.getString("lastname"), d.getInteger("id"));
	}

	//alle Salesman mit einem bestimmten attribut finden und auslesen
	@Override
	public List<SalesMan> querySalesMan(String attribute, String key) {
		List<Document> d = salesmen.find(eq(key,attribute)).into(new ArrayList<Document>());
		List<SalesMan> s = new ArrayList<SalesMan>();
		for (int i = 0; i < d.size(); i++) {
			s.add(new SalesMan(d.get(i).getString("firstname"), d.get(i).getString("lastname"), d.get(i).getInteger("id")));
		}
		return s;
	}

	//alle PerformanceRecords eines Salesman anhand seiner id finden und auslesen
	@Override
	public List<EvaluationRecord> readEvaluationRecords(int sid) {
		List<EvaluationRecord> e = new ArrayList<EvaluationRecord>();
		List<Document> d = records.find(eq("id",sid)).into(new ArrayList<Document>());
		for (int i = 0; i < d.size(); i++) {
			e.add((EvaluationRecord) d.get(i).get("performance"));
		}
		return e;
	}
}
