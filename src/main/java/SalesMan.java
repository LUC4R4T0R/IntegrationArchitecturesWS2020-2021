import java.util.ArrayList;
import java.util.List;

public class SalesMan{
	
    private int id;
    private String firstname;
    private String lastname;
    private List<EvaluationRecord> records;
	
    //Constructor
	public SalesMan(String firstname, String lastname, int id) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.records = new ArrayList<EvaluationRecord>();
	}
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public List<EvaluationRecord> getRecords() {
		return records;
	}

    public String toString(){
        return id + ": " + firstname + " " + lastname;
    }

}