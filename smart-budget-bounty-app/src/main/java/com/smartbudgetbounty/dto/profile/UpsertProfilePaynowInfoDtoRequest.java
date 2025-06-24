package com.smartbudgetbounty.dto.profile;

import jakarta.validation.constraints.NotNull;

public class UpsertProfilePaynowInfoDtoRequest {
	@NotNull(message = "Account Id is required")
	private Long accountId;
	
	@NotNull(message = "Phone Number is required.")
	private String phoneNumber;

	public UpsertProfilePaynowInfoDtoRequest(
			Long accountId,
			String phoneNumber) {
		super();
		this.accountId = accountId;
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}