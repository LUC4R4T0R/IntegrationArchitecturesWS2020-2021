package de.hbrs.ia.repository;

import com.mongodb.client.MongoCollection;
import de.hbrs.ia.model.EvaluationRecord;
import de.hbrs.ia.model.EvaluationRecordEntry;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SalesManRecord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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

    /*-------------------------------------------------------------Salesman-------------------------------------------------------------------*/

    /**
     * Inserts a new Salesman into the database.
     *
     * @param record The salesman that should be inserted into the database.
     */
    @Override
    public void createSalesMan(SalesMan record) {
        //is there a salesman with the same id?
        if (salesmen.find(eq("_id", record.getId())).first() != null) {
            throw new IllegalArgumentException("Id already exists");
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
        SalesMan s = salesmen.find(eq("_id", sid)).first();
        //no object found with this id:
        if (s == null) {
            throw new NoSuchElementException();
        }
        return s;
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
        List<SalesMan> s = salesmen.find(eq(key, attribute)).into(new ArrayList<>());
        //no object found with this id:
        if (s.isEmpty()) {
            throw new NoSuchElementException();
        }
        return s;
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
        SalesMan ls = salesmen.findOneAndReplace(eq("_id", s.getId()), s);
        //no object found with this id:
        if (ls == null) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Deletes the salesman with the given attribute and its records.
     *
     * @param sid The Id of the salesman that will be deleted.
     */
    @Override
    public void deleteSalesMen(int sid) {
        SalesMan s = salesmen.findOneAndDelete(eq("_id", sid));
        records.deleteMany(eq("salesmanId", sid));
        if (s == null) {
            throw new NoSuchElementException();
        }
    }

    /*-------------------------------------------------------------EvaluationRecord-------------------------------------------------------------------*/

    /**
     * Inserts a new PerformanceRecord into the database.
     *
     * @param record The evaluationrecord that should be inserted into the database.
     * @param sid    The Id of the salesman that received this evaluationrecord.
     */
    @Override
    public void addPerformanceRecord(EvaluationRecord record, int sid) {
        if (records.find(and(eq("salesmanId", sid), eq("performance.year", record.getYear()))).first() != null) {
            throw new IllegalArgumentException("For this salesman there is already a record with the given id-year combination.");
        }
        records.insertOne(new SalesManRecord(sid, record));
    }

    /**
     * Finds the PerformanceRecord with the given attribute.
     *
     * @param sid  The Id of the salesman who the PerformanceRecord search is about.
     * @param year The year the record was created.
     * @return This method returns the record of this year.
     */
    @Override
    public EvaluationRecord getOneRecord(int sid, int year) {
        SalesManRecord temp = records.find(and(eq("salesmanId", sid), eq("performance.year", year))).first();
        if (temp == null) {
            //errorhandling in controller (null must be returned here)
            return null;
        }
        return temp.getPerformance();
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
        SalesManRecord sr = records.findOneAndReplace(and(eq("salesmanId", srecord.getSalesmanId()), eq("performance.year", srecord.getPerformance().getYear())), srecord);
        if (sr == null) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Deletes the salesman with the given attribute.
     *
     * @param sid  The Id of the salesman that evaluationrecord will be deleted.
     * @param year The year of the evaluationrecord that will be deleted.
     */
    @Override
    public void deleteEvaluationRecord(int sid, int year) {
        SalesManRecord sr = records.findOneAndDelete(and(eq("salesmanId", sid), eq("performance.year", year)));
        if (sr == null) {
            throw new NoSuchElementException();
        }
    }

    /*-------------------------------------------------------------EvaluationRecordEntry-------------------------------------------------------------------*/

    /**
     * Creates an entry with the given attributes.
     *
     * @param id   The Id of the salesman that evaluationrecordentry will be created.
     * @param year The year of the evaluationrecordentry that will be created.
     * @param ere  The new evaluationrecordentry.
     */
    @Override
    public void addRecordEntry(int id, int year, EvaluationRecordEntry ere) {
        SalesManRecord record1 = records.find(and(eq("salesmanId", id), eq("performance.year", year))).first();
        if (record1 != null) {
            EvaluationRecord record = record1.getPerformance();
            record.getPerformance().add(ere);
            this.updateEvaluationRecord(new SalesManRecord(id, record));
        } else {
            throw new IllegalArgumentException("For this id-year combination exists no record.");
        }
    }

    /**
     * Returns all entries with the given attributes.
     *
     * @param id   The Id of the salesman that evaluationrecordentries is searched for.
     * @param year The year of the evaluationrecordentries that is searched for.
     */
    @Override
    public List<EvaluationRecordEntry> getAllEntrys(int id, int year) {
        SalesManRecord record = records.find(and(eq("salesmanId", id), eq("performance.year", year))).first();
        if (record != null) {
            EvaluationRecord r = record.getPerformance();
            return r.getPerformance();
        }
        throw new NoSuchElementException();
    }

    /**
     * Returns the entry with the given attributes.
     *
     * @param id   The Id of the salesman that evaluationrecordentry is searched for.
     * @param year The year of the evaluationrecordentry that is searched for.
     * @param name The name of the evaluationrecordentry that is searched for.
     */
    @Override
    public EvaluationRecordEntry getOneEntry(int id, int year, String name) {
        SalesManRecord record = records.find(and(eq("salesmanId", id), eq("performance.year", year))).first();
        if (record != null) {
            EvaluationRecord er = record.getPerformance();
            List<EvaluationRecordEntry> entries = er.getPerformance();
            int index = entries.indexOf(new EvaluationRecordEntry(0, 0, name));
            if (index >= 0) return entries.get(index);
            else throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    /**
     * Updates the entry with the given attributes.
     *
     * @param id   The Id of the salesman that evaluationrecordentry will be updated.
     * @param year The year of the evaluationrecordentry that will be updated.
     * @param ere  The new evaluationrecordentry.
     */
    @Override
    public void updateEntry(int id, int year, EvaluationRecordEntry ere) {
        SalesManRecord record = records.find(and(eq("salesmanId", id), eq("performance.year", year))).first();
        if (record != null) {
            List<EvaluationRecordEntry> entries = record.getPerformance().getPerformance();
            int index = entries.indexOf(new EvaluationRecordEntry(0, 0, ere.getName()));
            if (index >= 0) {
                EvaluationRecordEntry entry = entries.get(index);
                entry.setActual(ere.getActual());
                entry.setTarget(ere.getTarget());
                this.updateEvaluationRecord(new SalesManRecord(id, record.getPerformance()));
            }
            else throw new NoSuchElementException();
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Deletes the entry with the given attributes.
     *
     * @param id   The Id of the salesman that evaluationrecordentry will be deleted.
     * @param year The year of the evaluationrecordentry that will be deleted.
     * @param name The name of the evaluationrecordentry that will be deleted.
     */
    @Override
    public void deleteEntry(int id, int year, String name) {
        SalesManRecord record = records.find(and(eq("salesmanId", id), eq("performance.year", year))).first();
        if (record != null) {
            EvaluationRecord er = record.getPerformance();
            List<EvaluationRecordEntry> entries = er.getPerformance();
            int index = entries.indexOf(new EvaluationRecordEntry(0, 0, name));
            if (index >= 0) {
                entries.remove(index);
                er.setPerformance(entries);
                this.updateEvaluationRecord(new SalesManRecord(id, er));
            }
            else throw new NoSuchElementException();
        } else {
            throw new NoSuchElementException();
        }
    }
}
