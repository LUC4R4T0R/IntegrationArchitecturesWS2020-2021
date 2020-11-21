package de.hbrs.ai.model;

import org.bson.Document;

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
    private final String firstname;
    private final String lastname;

    /**
     * Constructor, that creates a salesman-Object with the given attributes.
     */
    public SalesMan(String firstname, String lastname, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
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
     * This method takes this salesman and puts its attributes into a Document.
     *
     * @return Returns the Document witch is build out of this salesman attributes.
     */
    public Document toDocument() {
        Document d = new Document();
        d.append("firstname", this.firstname);
        d.append("lastname", this.lastname);
        d.append("id", this.id);
        return d;
    }

    /**
     * Compares this salesman to the given one.
     *
     * @param s The dsalesman to compare with.
     * @return Returns true if the salesman are equal and false if not.
     */
    public boolean equals(SalesMan s) {
        return this.lastname.equals(s.getLastname()) && this.firstname.equals(s.getFirstname()) && this.id == s.getId();
    }

}