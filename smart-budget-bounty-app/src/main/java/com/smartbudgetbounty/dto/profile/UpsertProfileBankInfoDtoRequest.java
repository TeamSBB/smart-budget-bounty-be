package com.smartbudgetbounty.dto.profile;

import jakarta.validation.constraints.NotNull;

public class UpsertProfileBankInfoDtoRequest {
	@NotNull(message = "Account Id is required")
	private Long accountId;
	
	@NotNull(message = "Branch Name is required.")
	private String branchName;
	
	@NotNull(message = "Bank Account Number is required.")
	private String bankAccountNumber;
	
	@NotNull(message = "Bank Key is required.")
	private String bankKey;
	
	@NotNull(message = "Beneficiary is required.")
	private String beneficiaryName;
	
	@NotNull(message = "Account Type is required.")
	private String accountType;
	
	public UpsertProfileBankInfoDtoRequest(
			Long accountId,
			String branchName,
			String bankAccountNumber,
			String bankKey,
			String beneficiaryName,
			String accountType) {
		super();
		this.accountId = accountId;
		this.branchName = branchName;
		this.bankAccountNumber = bankAccountNumber;
		this.bankKey = bankKey;
		this.beneficiaryName = beneficiaryName;
		this.accountType = accountType;
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

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
