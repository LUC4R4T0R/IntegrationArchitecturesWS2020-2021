package de.hbrs.ia.model;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class that provides the Methods to create, read, delete and update
 * the MongoDB entries of the salesmen and there personal records.
 */
public class SalesMan {

    /**
     * The MongoDB-Collection for the salesmen.
     */
    private int id;
    private String firstname;
    private String lastname;

    /**
     * Constructor, that creates a salesman-Object with the given attributes.
     */
    public SalesMan(String firstname, String lastname, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    /**
     * Default constructor
     */
    public SalesMan() {}

    /**
     * Returns the id of this salesman.
     *
     * @return This method has no return-value.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of this salesman.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the firstname of this salesman.
     *
     * @return Returns the firstname of this salesman.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Returns the lastname of this salesman.
     *
     * @return Returns the lastname of this salesman.
     */
    public String getLastname() {
        return lastname;
    }


    /**
     * Method to display a salesman cleanly.
     *
     * @return Returns a readable String that describes the salesmann.
     */
    public String toString() {
        return id + ": " + firstname + " " + lastname;
    }

    /**
     * Compares this salesman to the given one.
     *
     * @param s The dsalesman to compare with.
     * @return Returns true if the salesman are equal and false if not.
     */
    public boolean equals(SalesMan s) {
        return this.getLastname().equals(s.getLastname()) && this.getFirstname().equals(s.getFirstname()) && this.getId() == s.getId();
    }

    /*
                                                                            must exist cause of MongoDb automatic toJson
    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}