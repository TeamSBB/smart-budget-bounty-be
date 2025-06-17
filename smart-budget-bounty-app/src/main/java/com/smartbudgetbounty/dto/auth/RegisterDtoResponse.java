package com.smartbudgetbounty.dto.auth;

public class RegisterDtoResponse {    
	private String username; 

	public RegisterDtoResponse(String username) {
        this.username = username;
    }
 
    // Getters and Setters
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
}
