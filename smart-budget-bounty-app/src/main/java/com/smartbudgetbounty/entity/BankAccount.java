package com.smartbudgetbounty.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String branchName;
	private String bankAccountNumber;
	private String bankKey;
	private String beneficiaryName;
	private String accountType;
	
	private Instant createdAt;
	private Instant updatedAt;

	// Note: No need to reference Account here, because will cause circular dependency	
	public BankAccount() {}
	
	public BankAccount(String branchName, String bankAccountNumber,
			String bankKey, String beneficiaryName, String accountType,
			Instant createdAt, Instant updatedAt) {
		super();
		this.branchName = branchName;
		this.bankAccountNumber = bankAccountNumber;
		this.bankKey = bankKey;
		this.beneficiaryName = beneficiaryName;
		this.accountType = accountType;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", branchName=" + branchName
				+ ", bankAccountNumber=" + bankAccountNumber + ", bankKey="
				+ bankKey + ", beneficiaryName=" + beneficiaryName
				+ ", accountType=" + accountType + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
}


