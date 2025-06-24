package com.smartbudgetbounty.dto.profile;

import jakarta.validation.constraints.NotNull;

public class UpsertProfileCardInfoDtoRequest {
	@NotNull(message = "Account Id is required")
	private Long accountId;
	
	@NotNull(message = "Card Number is required.")
	private String cardNumber;
	
	@NotNull(message = "Card Holder Name is required.")
	private String cardHolderName;
	
	@NotNull(message = "Expiry Date is required.")
	private String expiryDate;
	
	@NotNull(message = "Billing Address is required.")
	private String billingAddress;

	public UpsertProfileCardInfoDtoRequest(
			Long accountId,
			String cardNumber,
			String cardHolderName,
			String expiryDate,
			String billingAddress) {
		super();
		this.accountId = accountId;
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expiryDate = expiryDate;
		this.billingAddress = billingAddress;
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

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
