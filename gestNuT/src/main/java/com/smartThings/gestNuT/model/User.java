package com.smartThings.gestNuT.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int phoneNumber;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();

    public User() { 
        
    }

    public User(String username, String email, String pass, String name, int phone) {
        this.userName = username;
        this.email = email;
        this.password = pass;
        this.name = name;
        this.phoneNumber = phone;
    }

    @Override
    public String toString(){
        String user = String.format(
            "User[ID: %d, UserName: '%s', Email: '%s', Pass: '%s', Name: '%s', Phone: '%s']%n",
            id, userName, email, password, name, phoneNumber);
        if(addresses != null){
            for(Address address : addresses){
                user += address.toString();
                /*String.format(
                    "Address[id: %d, Name: %s, Street: %s, PostalCode: %s, City: %s, Notes: %s]%n", 
                    address.getID(), address.getName(), address.getStreet(), address.getPostalCode(), address.getCity(), address.getNotes());*/      
            }
        }
        return user;
    }
}
