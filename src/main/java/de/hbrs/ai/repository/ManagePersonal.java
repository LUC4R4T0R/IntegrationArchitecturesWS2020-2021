package de.hbrs.ai.repository;

import com.mongodb.client.MongoCollection;
import de.hbrs.ai.model.EvaluationRecord;
import de.hbrs.ai.model.SalesMan;
import de.hbrs.ai.model.SalesManRecord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class that provides the Methods to create, read, delete and update
 * the MongoDB entries of the salesmen and there personal records.
 */
public class ManagePersonal implements ManagePersonalInterface {

    /**
     * The MongoDB-Collection for the salesmen.
     */
    private final MongoCollection<SalesMan> salesmen;

    /**
     * The MongoDB-Collection for the personal records of every salesman.
     */
    private final MongoCollection<SalesManRecord> records;

    /**
     * Constructor, that creates a ManagePersonal-Object with the given salesmen and
     * record MongoDB-Collection.
     */
    public ManagePersonal(MongoCollection<SalesMan> salesmen, MongoCollection<SalesManRecord> record) {
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
     * @param record The salesman that should be inserted into the database.
     */
    @Override
    public void createSalesMan(SalesMan record) {
        if (readSalesMan(record.getId()) != null) {
            throw new IllegalArgumentException("Id existiert bereits");
        }
        salesmen.insertOne(record);
    }

    /**
     * Finds the salesman with the given Id.
     *
     * @param sid The Id of the salesman who the search is about.
     * @return This method returns the salesman with the given ID if he exists. If
     * he can be found in the database the return-value is null.
     */
    @Override
    public SalesMan readSalesMan(int sid) {
        return salesmen.find(eq("_id", sid)).first();
    }

    /**
     * Finds all salesman with the given attribute.
     *
     * @param attribute The salesman-attribute that is searched for.
     * @param key       The key of the attribute.
     * @return This method returns a List of the found salesman with the given
     * attribute. If there is no salesman with this attribute the
     * return-value is null.
     */
    @Override
    public List<SalesMan> querySalesMan(String attribute, String key) {
        return salesmen.find(eq(key, attribute)).into(new ArrayList<>());
    }

    /**
     * Finds, orders and returns all salesman in the database.
     *
     * @return This method returns an ordered-List of all salesman in the database.
     */
    @Override
    public List<SalesMan> getAllSalesMen() {
        List<SalesMan> s = salesmen.find().into(new ArrayList<>());
        s.sort(Comparator.comparing(SalesMan::getId));
        return s;
    }

    /**
     * Updates the salesman with the given id attribute.
     *
     * @param s The updated salesman.
     */
    @Override
    public void updateSalesMen(SalesMan s) {
        salesmen.findOneAndReplace(eq("_id", s.getId()), s);
    }

    /**
     * Deletes the salesman with the given attribute and its records.
     *
     * @param sid The Id of the salesman that will be deleted.
     */
    @Override
    public void deleteSalesMen(int sid) {
        salesmen.findOneAndDelete(eq("_id", sid));
        records.deleteMany(eq("_id", sid));
    }

    /**
     * Inserts a new PerformanceRecord into the database.
     *
     * @param record The evaluationrecord that should be inserted into the database.
     * @param sid    The Id of the salesman that received this evaluationrecord.
     */
    @Override
    public void addPerformanceRecord(EvaluationRecord record, int sid) {
        if (getOneRecord(sid, record.getYear()) != null){
            throw new IllegalArgumentException("Für dieses SalesMan existiert bereits ein record für dieses Jahr");
        }
        records.insertOne(new SalesManRecord(sid, record));
    }

    /**
     * Finds the PerformanceRecord with the given attribute.
     *
     * @param sid The Id of the salesman who the PerformanceRecord search is about.
     * @param year The year the record was created.
     * @return This method returns the record of this year.
     */
    @Override
    public EvaluationRecord getOneRecord(int sid, int year){
        return records.find(and(eq("_id", sid), eq("performance.year", year))).first().getPerformance();
    }

    /**
     * Finds all PerformanceRecords with the given attribute.
     *
     * @param sid The Id of the salesman who the PerformanceRecord search is about.
     * @return This method returns an ordered-List of the found salesman-PerformanceRecords
     * with the given attribute. If there is none, the return-value is null.
     */
    @Override
    public List<EvaluationRecord> readEvaluationRecords(int sid) {
        List<EvaluationRecord> e = new ArrayList<>();
        List<SalesManRecord> d = records.find(eq("salesmanId", sid)).into(new ArrayList<>());

        for (SalesManRecord salesManRecord : d) {
            e.add(salesManRecord.getPerformance());
        }

        e.sort(Comparator.comparing(EvaluationRecord::getYear));
        return e;
    }

    /**
     * Updates the evaluationrecord with the given id attribute.
     *
     * @param srecord The updated salesman-Record.
     */
    @Override
    public void updateEvaluationRecord(SalesManRecord srecord) {
        records.findOneAndReplace(and(eq("salesmanId", srecord.getSalesmanId()), eq("performance.year", srecord.getPerformance().getYear())), srecord);
    }

    /**
     * Deletes the salesman with the given attribute.
     *
     * @param sid  The Id of the salesman that de.hbrs.ai.model.EvaluationRecord will be deleted.
     * @param year The year of the evaluationrecord will be deleted.
     */
    @Override
    public void deleteEvaluationRecord(int sid, int year) {
        records.findOneAndDelete(and(eq("salesmanId", sid), eq("performance.year", year)));
    }
}
