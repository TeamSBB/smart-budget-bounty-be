package com.smartbudgetbounty.dto.profile;

import java.time.Instant;

public class UpsertProfileBankInfoDtoResponse {	
	private Long bankAccountId;
	
	private String branchName;
	private String bankAccountNumber;
	private String bankKey;
	private String beneficiaryName;
	private String accountType;
	
	private Instant createdAt;
	private Instant updatedAt;
	
	public UpsertProfileBankInfoDtoResponse(Long bankAccountId, String branchName,
			String bankAccountNumber, String bankKey, String beneficiaryName,
			String accountType, Instant createdAt, Instant updatedAt) {
		super();
		this.bankAccountId = bankAccountId;
		this.branchName = branchName;
		this.bankAccountNumber = bankAccountNumber;
		this.bankKey = bankKey;
		this.beneficiaryName = beneficiaryName;
		this.accountType = accountType;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long id) {
		this.bankAccountId = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankKey() {
		return bankKey;
	}

	public void setBankKey(String bankKey) {
		this.bankKey = bankKey;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}
