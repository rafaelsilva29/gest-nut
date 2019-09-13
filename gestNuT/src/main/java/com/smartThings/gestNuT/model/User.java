package com.smartThings.gestNuT.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUser;

    @Column
    private String userName;

    @Column
    private String email;

    @Column
    private String passWord;

    @Column
    private String name;

    @Column
    private int phoneNumber;

    protected User() { 
    }

    public User(String username, String email, String pass, String name, int phone) {
        this.userName = username;
        this.email = email;
        this.passWord = pass;
        this.name = name;
        this.phoneNumber = phone;
    }

    @Override
    public String toString(){
        return String.format("User[ID: %d, UserName: '%s', Email: '%s', Pass: '%s', Name: '%s', Phone: '%s']",idUser, userName, email, passWord, name, phoneNumber);
    }
}
