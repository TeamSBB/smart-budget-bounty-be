package com.smartbudgetbounty.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDtoRequest {
	@NotBlank(message="email must not be blank")
	@Size(max = 50, message = "Email must not exceed 50 characters")
    private String email;
	
	@NotBlank(message="password must not be blank")
	@Size(max = 50, message = "Password must not exceed 50 characters")
    private String password;
    
	public LoginDtoRequest() {
		
	}
	
	public LoginDtoRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
