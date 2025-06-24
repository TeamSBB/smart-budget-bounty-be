package com.smartbudgetbounty.dto.auth;

public class LoginDtoResponse {    
	private String token;
	private Long userId;
	private Long accountId;
	private String username;
	private String email;
	private String address;	
	private String contactNumber;	
	private String firstName;
	private String lastName;
	
    public LoginDtoResponse(String token, Long userId, Long accountId, String username, String email,
			String address, String contactNumber,
			String firstName, String lastName) {
		super();
		this.token = token;
		this.userId = userId;
		this.accountId = accountId;
		this.username = username;
		this.email = email;
		this.address = address;
		this.contactNumber = contactNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
