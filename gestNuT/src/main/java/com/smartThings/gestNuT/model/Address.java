package com.smartThings.gestNuT.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;
    
    @Column
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public Address() {

    }

    public Address(String name, String street, String postal, String city, String notes) {
        this.name = name;
        this.street = street;
        this.postalCode = postal;
        this.city = city;
        this.notes = notes;
    }

    // Getters
    public int getID() { return this.id; }
    public String getName() { return this.name; }
    public String getStreet() { return this.street; }
    public String getPostalCode() { return this.postalCode; }
    public String getCity() { return this.city; }
    public String getNotes() { return this.notes; }

    @Override
    public String toString() {
        String address = String.format(
            "Address[id: %d, Name: %s, Street: %s, PostalCode: %s, City: %s, Notes: %s]", 
            id, name, street, postalCode, city, notes);
        return address;
    }
}