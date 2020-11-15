import java.util.List;

public interface ManagePersonalInterface {

    public void createSalesMan( SalesMan record );
    
    public SalesMan readSalesMan( int sid );
    
    public List<SalesMan> querySalesMan(String attribute , String key );

    public List<SalesMan> getAllSalesMen();
    
    void updateSalesMen(int sid, String key, String attribute);

    void deleteSalesMen(int sid);
    
    public void addPerformanceRecord( EvaluationRecord record , int sid );
    
    public List<EvaluationRecord> readEvaluationRecords( int sid );

    void updateEvaluationRecord(int id, int year, String key, int attribute);

    void deleteEvaluationRecord(int sid, int year);

}
