package com.smartbudgetbounty.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDtoRequest {
	@NotBlank(message="username must not be blank")
	@Size(max = 50, message = "Data must not exceed 50 characters")
    private String username;
	
	@NotBlank(message="password must not be blank")
	@Size(max = 50, message = "Password must not exceed 50 characters")
    private String password;
    
	public LoginDtoRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
