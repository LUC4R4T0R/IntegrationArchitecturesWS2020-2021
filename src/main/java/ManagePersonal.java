import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

/**
 * 
 * @author jbrill2s, lringh2s
 * 
 *         Class that provides the Methods to create, read, delete and update
 *         the MongoDB entries of the salesmen and there personal records.
 * 
 */
public class ManagePersonal implements ManagePersonalInterface {

	/**
	 * The MongoDB-Collection for the salesmen.
	 */
	private MongoCollection<Document> salesmen;

	/**
	 * The MongoDB-Collection for the personal records of every salesman.
	 */
	private MongoCollection<Document> records;

	/**
	 * Constructor, that creates a ManagePersonal-Object with the given salesmen and
	 * record MongoDB-Collection.
	 */
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

	/**
	 * Inserts a new Salesman into the database.
	 * 
	 * @param record The SalesMan that should be inserted into the database.
	 * 
	 * @return This method has no return-value.
	 */
	@Override
	public void createSalesMan(SalesMan record) {
		salesmen.insertOne(record.toDocument());
	}

	/**
	 * Finds the SalesMan with the given Id.
	 * 
	 * @param sid The Id of the SalesMan who the search is about.
	 * 
	 * @return This method returns the SalesMan with the given ID if he exists. If
	 *         he can be found in the database the return-value is null.
	 */
	@Override
	public SalesMan readSalesMan(int sid) {
		Document d = salesmen.find(eq("id", sid)).first();
		if (d != null) {
			return new SalesMan(d.getString("firstname"), d.getString("lastname"), d.getInteger("id"));
		}
		return null;
	}

	/**
	 * Finds all SalesMan with the given attribute.
	 * 
	 * @param attribute The SalesMan-attribute that is searched for.
	 * @param key       The key of the attribute.
	 * 
	 * @return This method returns a List of the found SalesMan with the given
	 *         attribute. If there is no SalesMan with this attribute the
	 *         return-value is null.
	 */
	@Override
	public List<SalesMan> querySalesMan(String attribute, String key) {
		List<Document> d = salesmen.find(eq(key, attribute)).into(new ArrayList<Document>());
		List<SalesMan> s = new ArrayList<SalesMan>();
		for (int i = 0; i < d.size(); i++) {
			s.add(new SalesMan(d.get(i).getString("firstname"), d.get(i).getString("lastname"),
					d.get(i).getInteger("id")));
		}
		return s;
	}

	/**
	 * Finds and returns all SalesMan in the database.
	 * 
	 * @return This method returns a List of all SalesMan in the database.
	 */
	public List<SalesMan> getAllSalesMen() {
		List<Document> d = salesmen.find().into(new ArrayList<Document>());
		List<SalesMan> s = new ArrayList<SalesMan>();
		for (int i = 0; i < d.size(); i++) {
			s.add(new SalesMan(d.get(i).getString("firstname"), d.get(i).getString("lastname"),
					d.get(i).getInteger("id")));
		}
		return s;
	}

	/**
	 * Updates the SalesMan with the given id attribute.
	 * 
	 * @param sid       The Id of the SalesMan that will be updated.
	 * @param key       The key of the attribute that will be updated.
	 * @param attribute The new value for the SalesMan.
	 * 
	 * @return This method has no return-value.
	 */
	public void updateSalesMen(int sid, String key, String attribute) {
		salesmen.updateOne(eq("id", sid), new Document("$set", new Document(key, attribute)));
	}

	/**
	 * Deletes the SalesMan with the given attribute.
	 * 
	 * @param sid The Id of the SalesMan that will be deleted.
	 * 
	 * @return This method has no return-value.
	 */
	public void deleteSalesMen(int sid) {
		salesmen.findOneAndDelete(eq("id", sid));
	}

	/**
	 * Inserts a new PerformanceRecord into the database.
	 * 
	 * @param record The EvaluationRecord that should be inserted into the database.
	 * @param sid    The Id of the SalesMan that received this EvaluationRecord.
	 * 
	 * @return This method has no return-value.
	 */
	@Override
	public void addPerformanceRecord(EvaluationRecord record, int sid) {
		records.insertOne(new SalesManRecord(sid, record).toDocument());
	}

	/**
	 * Finds all PerformanceRecords with the given attribute.
	 * 
	 * @param sid The Id of the SalesMan who the PerformanceRecord search is about.
	 * 
	 * @return This method returns a List of the found SalesMan-PerformanceRecords
	 *         with the given attribute. If there is none, the return-value is null.
	 */
	@Override
	public List<EvaluationRecord> readEvaluationRecords(int sid) {
		List<EvaluationRecord> e = new ArrayList<EvaluationRecord>();
		List<Document> d = records.find(eq("id", sid)).into(new ArrayList<Document>());
		for (int i = 0; i < d.size(); i++) {
			e.add(new EvaluationRecord((int[]) d.get(i).get("performance"), d.get(i).getInteger("year")));
		}
		return e;
	}

	/**
	 * Updates the EvaluationRecord with the given id attribute.
	 * 
	 * @param id        The id of the SalesMan-Record that will be updated.
	 * @param year      The year of the SalesMan-Record that will be updated.
	 * @param key       The key of the attribute that will be updated.
	 * @param attribute The new value.
	 * 
	 * @return This method has no return-value.
	 */
	public void updateEvaluationRecord(int id, int year, String key, int attribute) {
		salesmen.findOneAndUpdate(and(eq("id", id), eq("year", year)), new Document("$set", new Document(key, attribute)));
	}

	/**
	 * Deletes the SalesMan with the given attribute.
	 * 
	 * @param sid    The Id of the SalesMan that EvaluationRecord will be deleted.
	 * @param delete The String of the SalesMan that EvaluationRecord will be
	 *               deleted.
	 * 
	 * @return This method has no return-value.
	 */
	public void deleteEvaluationRecord(int sid, String delete) {
		records.findOneAndDelete(eq("records", delete));
	}
}
