package com.smartbudgetbounty.dto.profile;

public class UpsertProfilePaynowInfoDtoResponse {
	private Long userId;
	private String phoneNumber;

	public UpsertProfilePaynowInfoDtoResponse(
			Long userId,
			String phoneNumber) {
		super();
		this.userId = userId;
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
