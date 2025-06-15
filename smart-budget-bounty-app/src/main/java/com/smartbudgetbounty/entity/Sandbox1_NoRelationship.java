package com.smartbudgetbounty.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "s1_no_relationship")
public class Sandbox1_NoRelationship {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;					// Note: Use Long instead of long. Because Long can accept null. Use Wrapper class instead of primitives
	
	@Column(name="data", length = 50)
	private String data;

	@Column(name="secret", length = 50)
	private String secret;

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

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
}
