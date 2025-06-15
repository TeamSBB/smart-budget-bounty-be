package com.smartbudgetbounty.dto.Sandbox1;

public class Sandbox1DtoResponse {
	private Long id;
	private String data;
	// Not exposing secret, because its secret. That's what make DTO different from Entity, we show only what's needed
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
