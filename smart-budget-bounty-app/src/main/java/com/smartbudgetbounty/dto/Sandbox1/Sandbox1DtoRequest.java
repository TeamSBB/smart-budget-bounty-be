package com.smartbudgetbounty.dto.Sandbox1;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Sandbox1DtoRequest {
	@NotBlank(message="Data must not be blank")
	@Size(max = 50, message = "Data must not exceed 50 characters")
	private String data;

	@NotBlank(message="secret must not be blank")
	@Size(max = 50, message = "secret must not exceed 50 characters")
	private String secret;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
}
