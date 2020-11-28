package de.hbrs.ia.repository;

import de.hbrs.ia.model.EvaluationRecord;
import de.hbrs.ia.model.EvaluationRecordEntry;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SalesManRecord;

import java.util.List;


/**
 * @author jbrill2s, lringh2s
 * <p>
 * This interface presents all the methods that are used to communicate with the mongodb.
 */
public interface ManagePersonalInterface {

    void createSalesMan(SalesMan record);

    SalesMan readSalesMan(int sid);

    List<SalesMan> querySalesMan(String attribute, String key);

    List<SalesMan> getAllSalesMen();

    void updateSalesMen(SalesMan s);

    void deleteSalesMen(int sid);

    void addPerformanceRecord(EvaluationRecord record, int sid);

    EvaluationRecord getOneRecord(int sid, int year);

    List<EvaluationRecord> readEvaluationRecords(int sid);

    void updateEvaluationRecord(SalesManRecord srecord);

    void deleteEvaluationRecord(int sid, int year);

    void addRecordEntry(int id, int year, EvaluationRecordEntry ere);

    List<EvaluationRecordEntry> getAllEntrys(int id, int year);

    EvaluationRecordEntry getOneEntry(int id, int year, String name);

    void updateEntry(int id, int year, EvaluationRecordEntry ere);

    void deleteEntry(int id, int year, String name);

}
