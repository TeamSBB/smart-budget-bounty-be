package com.smartbudgetbounty.dto.sandbox2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Sandbox2PersonDtoRequest {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;
 
    @NotBlank(message = "Passport number is mandatory")
    @Size(max = 20, message = "Passport number must be at most 20 characters")
    private String passportNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
 
    
}