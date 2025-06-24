package com.smartbudgetbounty.dto.profile;

public class GetProfileInfoDtoResponse {	
	// Bank Info
	private String branchName;
	private String bankAccountNumber;
	private String bankKey;
	private String beneficiaryName;
	private String accountType;

	// Card Info
	private String cardNumber;
	private String cardHolderName;
	private String expiryDate;
	private String billingAddress;
	
	// Paynow Info
	private String phoneNumber;

	public GetProfileInfoDtoResponse(String branchName,
			String bankAccountNumber, String bankKey, String beneficiaryName,
			String accountType, String cardNumber, String cardHolderName,
			String expiryDate, String billingAddress, String phoneNumber) {
		super();
		this.branchName = branchName;
		this.bankAccountNumber = bankAccountNumber;
		this.bankKey = bankKey;
		this.beneficiaryName = beneficiaryName;
		this.accountType = accountType;
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expiryDate = expiryDate;
		this.billingAddress = billingAddress;
		this.phoneNumber = phoneNumber;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
