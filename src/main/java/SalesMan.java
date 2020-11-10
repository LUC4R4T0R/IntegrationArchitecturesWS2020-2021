import org.bson.Document;

public class SalesMan{
	
    private int id;
    private String firstname;
    private String lastname;
	
    //Constructor
	public SalesMan(String firstname, String lastname, int id) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
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

    public String toString(){
        return id + ": " + firstname + " " + lastname;
    }
    
    //toDo
    public Document toDocument() {
		return new Document();
    	
    }

}