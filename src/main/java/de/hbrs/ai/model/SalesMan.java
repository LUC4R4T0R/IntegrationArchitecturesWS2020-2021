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
     * Constructor, that creates a de.hbrs.ai.model.SalesMan-Object with the given attributes.
     */
    public SalesMan(String firstname, String lastname, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    /**
     * Returns the id of this de.hbrs.ai.model.SalesMan.
     *
     * @return This method has no return-value.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of this de.hbrs.ai.model.SalesMan.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the firstname of this de.hbrs.ai.model.SalesMan.
     *
     * @return Returns the firstname of this de.hbrs.ai.model.SalesMan.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Returns the lastname of this de.hbrs.ai.model.SalesMan.
     *
     * @return Returns the lastname of this de.hbrs.ai.model.SalesMan.
     */
    public String getLastname() {
        return lastname;
    }


    /**
     * Method to display a de.hbrs.ai.model.SalesMan cleanly.
     *
     * @return Returns a readable String that describes the de.hbrs.ai.model.SalesMan.
     */
    public String toString() {
        return id + ": " + firstname + " " + lastname;
    }

    /**
     * This method takes this de.hbrs.ai.model.SalesMan and puts its attributes into a Document.
     *
     * @return Returns the Document witch is build out of this SalesMans attributes.
     */
    public Document toDocument() {
        Document d = new Document();
        d.append("firstname", this.firstname);
        d.append("lastname", this.lastname);
        d.append("id", this.id);
        return d;
    }

    /**
     * Compares this de.hbrs.ai.model.SalesMan to the given one.
     *
     * @param s The de.hbrs.ai.model.SalesMan to compare with.
     * @return Returns true if the SalesMen are equal and false if not.
     */
    public boolean equals(SalesMan s) {
        return this.lastname.equals(s.getLastname()) && this.firstname.equals(s.getFirstname()) && this.id == s.getId();
    }

}