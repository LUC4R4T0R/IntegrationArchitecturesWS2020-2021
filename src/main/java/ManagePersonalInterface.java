import java.util.List;

public interface ManagePersonalInterface {

    void createSalesMan(SalesMan record);

    SalesMan readSalesMan(int sid);

    List<SalesMan> querySalesMan(String attribute, String key);

    List<SalesMan> getAllSalesMen();

    void updateSalesMen(int sid, String firstname, String lastname);

    void deleteSalesMen(int sid);

    void addPerformanceRecord(EvaluationRecord record, int sid);

    List<EvaluationRecord> readEvaluationRecords(int sid);

    void updateEvaluationRecord(int id, int year, String key, int attribute);

    void deleteEvaluationRecord(int sid, int year);

}
