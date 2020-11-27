package de.hbrs.ia.model;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * Class that provides the Methods to create, read, delete and update
 * the MongoDB entries of the salesmen and there personal records.
 */
public class SalesMan {

    /**
     * The id of the salesman
     */
    private int id;

    /**
     * The firstname of the salesman
     */
    private String firstname;

    /**
     * The lastname of the salesman
     */
    private String lastname;

    /**
     * Constructor, that creates a salesman-object with the given attributes.
     */
    public SalesMan(String firstname, String lastname, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    /**
     * Default constructor
     */
    public SalesMan() {
    }

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
     * Sets the firstname of this salesman.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
     * Sets the lastname of this salesman.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    /**
     * Method to display a salesman cleanly.
     *
     * @return Returns a readable String that describes the salesman.
     */
    public String toString() {
        return id + ": " + firstname + " " + lastname;
    }

    /**
     * Compares this salesman to the given one.
     *
     * @param s The salesman to compare with.
     * @return Returns true if the salesmen are equal and false if not.
     */
    public boolean equals(SalesMan s) {
        return this.getLastname().equals(s.getLastname())
                && this.getFirstname().equals(s.getFirstname())
                && this.getId() == s.getId();
    }
}