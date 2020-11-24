package de.hbrs.ai.repository;

import de.hbrs.ai.model.EvaluationRecord;
import de.hbrs.ai.model.SalesMan;
import de.hbrs.ai.model.SalesManRecord;

import java.util.List;

public interface ManagePersonalInterface {

    void createSalesMan(SalesMan record);

    SalesMan readSalesMan(int sid);

    List<SalesMan> querySalesMan(String attribute, String key);

    List<SalesMan> getAllSalesMen();

    void updateSalesMen(SalesMan s);

    void deleteSalesMen(int sid);

    void addPerformanceRecord(EvaluationRecord record, int sid);

    SalesManRecord getOneRecord(int sid, int year);

    List<EvaluationRecord> readEvaluationRecords(int sid);

    void updateEvaluationRecord(SalesManRecord srecord);

    void deleteEvaluationRecord(int sid, int year);

}
