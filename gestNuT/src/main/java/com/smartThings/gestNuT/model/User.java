package com.smartThings.gestNuT.model;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

import com.smartThings.gestNuT.model.*;

@Data

@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /*@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<Address> addresses;*/

    protected User() { 
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
        return String.format("User[ID: %d, UserName: '%s', Email: '%s', Pass: '%s', Name: '%s', Phone: '%s']",id, userName, email, password, name, phoneNumber);
    }
}
