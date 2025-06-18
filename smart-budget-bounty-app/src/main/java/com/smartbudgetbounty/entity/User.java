package com.smartbudgetbounty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")  // Use escaped quotes to tell DB to treat it literally
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    
	private String address;
	private String contactNumber;
	private String email;
	private String firstName;
	private String lastName;

    public User() {}

    public User(Long id, String username, String password) {
    	this.id = id;
    	this.username = username;
    	this.password = password;
	}
    
    
    public User(Long id, String username, String password, String address,
			String contactNumber, String email, String firstName,
			String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	// Getters and Setters
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}