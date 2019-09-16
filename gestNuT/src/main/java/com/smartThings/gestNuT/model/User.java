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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
    private String email;

    @Column(name = "password", nullable = false)
    @Transient
    private String password;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
	@NotEmpty(message = "Please provide your last name")
	private String lastName;

    @Column(name = "phnone_number", nullable = false)
    private int phoneNumber;

    @Column(name = "confirmation_token")
	private String confirmationToken;

    @Column(name = "enabled")
	private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();

    public User() { 
        
    }

    public User(String username, String email, String pass,  String firstname, String lastname, int phone) {
        this.email = email;
        this.password = pass;
        this.firstName = firstname;
        this.lastName = lastname;
        this.phoneNumber = phone;
    }

    // Getters
    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPassword() { return password; }
    public String getConfirmationToken() { return confirmationToken; }
    public boolean getEnabled() { return enabled; }
    

    // Setters
	public void setConfirmationToken(String confirmationToken) { this.confirmationToken = confirmationToken; }
    public void setEnabled(boolean value) { this.enabled = value; }
    public void setId(int id) { this.id = id; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString(){
        String user = String.format(
            "User[ID: %d, FirstName: '%s', LastName: '%s', Email: '%s', Pass: '%s', Phone: '%s']%n",
            id, firstName, lastName, email, password, phoneNumber);
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
