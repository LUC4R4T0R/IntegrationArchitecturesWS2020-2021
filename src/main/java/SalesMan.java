import org.bson.Document;

public class SalesMan{
	
    private int id;
    private String firstname;
    private String lastname;
	
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
    
    public Document toDocument() {
    	Document d = new Document();
    	d.append("firstname", this.firstname);
		d.append("lastname", this.lastname);
		d.append("id", this.id);
    	return d;
    }
    
    public boolean equals(SalesMan s) {
    	return this.lastname.equals(s.getLastname()) && this.firstname.equals(s.getFirstname()) && this.id == s.getId();
    }

}