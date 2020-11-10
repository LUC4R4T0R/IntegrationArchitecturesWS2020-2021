import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class ManagePersonal implements ManagePersonalInterface {

	@Override
	public void createSalesMan(SalesMan record) {
		//Create the SalesMan document that can to be stored in the Database
		Document document = new Document();
		document.append("firstname", record.getFirstname());
		document.append("lastname", record.getLastname());
		document.append("id", record.getId());
		document.append("records", record.getRecords());

		//string the document in the Database
		Main.getSalesmen().insertOne(document);
	}

	@Override
	//funktioniert wahrscheinlich nicht
	public void addPerformanceRecord(EvaluationRecord record, int sid) {
		Document d = Main.getSalesmen().find(eq("id", ""+ sid)).first();
		List<EvaluationRecord> e = (List<EvaluationRecord>) d.get("record");
		e.add(record);
	}

	@Override
	public SalesMan readSalesMan(int sid) {
		Document d = Main.getSalesmen().find(eq("id", ""+ sid)).first();
		return new SalesMan(d.getString("firstname"), d.getString("lastname"), d.getInteger("id"));
	}

	@Override
	public List<SalesMan> querySalesMan(String attribute, String key) {
		List<Document> d = Main.getSalesmen().find(eq(key,attribute)).into(new ArrayList<Document>());
		List<SalesMan> s = new ArrayList<SalesMan>();
		for (int i = 0; i < d.size(); i++) {
			s.add(new SalesMan(d.get(i).getString("firstname"), d.get(i).getString("lastname"), d.get(i).getInteger("id"), (EvaluationRecord) d.get(i).get("records")));
		}
		return s;
	}

	@Override
	public EvaluationRecord readEvaluationRecords(int sid) {
		return null;
	}
}
