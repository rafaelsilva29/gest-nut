package com.smartThings.gestNuT.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "address")
public class Address { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "street", nullable = false)
    @NotEmpty(message = "Please provide a Street")
    private String street;

    @Column(name = "postal_code", nullable = false)
    @NotEmpty(message = "Please provide a Postal Code")
    private String postalCode;

    @Column(name = "city", nullable = false)
    @NotEmpty(message = "Please provide a City")
    private String city;
    
    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "address")
    private Parcel parcel;
    
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

    // Setters
    public void setName(String name) { this.name = name; } 
    public void setStreet(String street) { this.street = street; } 
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; } 
    public void setCity(String city) { this.city = city; } 
    public void setNotes(String notes) { this.notes = notes; } 

    @Override
    public String toString() {
        String address = String.format(
            "Address[id: %d, Name: %s, Street: %s, PostalCode: %s, City: %s, Notes: %s]", 
            id, name, street, postalCode, city, notes);
        return address;
    }
    
}