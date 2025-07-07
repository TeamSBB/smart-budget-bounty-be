package com.smartbudgetbounty.dto.auth;

public class RegisterDtoRequest {
//	@NotBlank(message="username must not be blank")
//	@Size(max = 50, message = "Data must not exceed 50 characters")
    private String username;
	
//	@NotBlank(message="password must not be blank")
//	@Size(max = 50, message = "Password must not exceed 50 characters")
    private String password;

//	@NotBlank(message="address must not be blank")
//	@Size(max = 50, message = "address must not exceed 50 characters")
	private String address;
	
//	@NotBlank(message="contactNumber must not be blank")
//	@Size(max = 50, message = "contactNumber must not exceed 50 characters")
	private String contactNumber;
	
//	@NotBlank(message="email must not be blank")
//	@Size(max = 50, message = "email must not exceed 50 characters")
	private String email;
	
//	@NotBlank(message="firstName must not be blank")
//	@Size(max = 50, message = "firstName must not exceed 50 characters")
	private String firstName;
	
//	@NotBlank(message="lastName must not be blank")
//	@Size(max = 50, message = "lastName must not exceed 50 characters")
	private String lastName;

	public RegisterDtoRequest() {}
	
	public RegisterDtoRequest(
			String username,
			String password,
			String address,
			String contactNumber,
			String email,
			String firstName,
			String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
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

	
}