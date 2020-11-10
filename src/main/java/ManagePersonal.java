import java.util.List;

import org.bson.Document;

public class ManagePersonal implements ManagePersonalInterface {

	@Override
	public void createSalesMan(SalesMan record) {

		Document document = new Document();
		document.append("firstname", record.getFirstname());
		document.append("lastname", record.getLastname());
		document.append("id", record.getId());

		Main.getSalesmen().insertOne(document);
	}

	@Override
	public void addPerformanceRecord(EvaluationRecord record, int sid) {

	}

	@Override
	public SalesMan readSalesMan(int sid) {
		return null;
	}

	@Override
	public List<SalesMan> querySalesMan(String attribute, String key) {
		return null;
	}

	@Override
	public EvaluationRecord readEvaluationRecords(int sid) {
		return null;
	}
}
