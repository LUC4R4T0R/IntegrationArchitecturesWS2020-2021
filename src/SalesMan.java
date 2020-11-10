package org.hbrs.ia.contract;

public class SalesMan{
    private int id;
    private String firstname;
    private String lastname;

    public SalesMan(id, firstname, lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String toString(){
        return id + ": " + firstname + " " + lastname;
    }
}