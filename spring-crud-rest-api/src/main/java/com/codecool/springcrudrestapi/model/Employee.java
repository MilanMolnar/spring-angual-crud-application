package com.codecool.springcrudrestapi.model;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String eamilId;

    public Employee(long id, String firstName, String lastName, String eamilId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eamilId = eamilId;
    }

    public Employee() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEamilId() {
        return eamilId;
    }

    public void setEamilId(String eamilId) {
        this.eamilId = eamilId;
    }

}
