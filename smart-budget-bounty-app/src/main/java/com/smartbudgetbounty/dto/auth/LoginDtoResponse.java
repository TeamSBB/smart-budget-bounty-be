package com.smartbudgetbounty.dto.auth;

public class LoginDtoResponse {    
	private String token;
	private Long id;
	private String username;
	private String email;
	private String address;	
	private String contactNumber;	
	private String firstName;
	private String lastName;
	
    public LoginDtoResponse(String token, Long id, String username, String email,
			String address, String contactNumber,
			String firstName, String lastName) {
		super();
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.address = address;
		this.contactNumber = contactNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
        return token;
    }
 
    public void setToken(String token) {
        this.token = token;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
    
    
}
