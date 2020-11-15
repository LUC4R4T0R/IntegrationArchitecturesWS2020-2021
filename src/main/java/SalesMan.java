import org.bson.Document;

/**
 * 
 * @author jbrill2s, lringh2s
 * 
 *         Class that provides the Methods to create, read, delete and update
 *         the MongoDB entries of the salesmen and there personal records.
 * 
 */
public class SalesMan {
	
	/**
	 * The MongoDB-Collection for the salesmen.
	 */
	private int id;
	private String firstname;
	private String lastname;

	/**
	 * Constructor, that creates a ManagePersonal-Object with the given salesmen and
	 * record MongoDB-Collection.
	 */
	public SalesMan(String firstname, String lastname, int id) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
	}

	/**
	 * Inserts a new Salesman into the database.
	 * 
	 * @param record The SalesMan that should be inserted into the database.
	 * 
	 * @return This method has no return-value.
	 */
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

	public String toString() {
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