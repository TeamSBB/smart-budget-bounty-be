package com.smartbudgetbounty.dto.profile;

import java.time.Instant;

public class UpsertProfileCardInfoDtoResponse {	
	private Long cardId;

	private String cardNumber;
	private String cardHolderName;
	private String expiryDate;
	private String billingAddress;
	
	private Instant createdAt;
	private Instant updatedAt;
	
	public UpsertProfileCardInfoDtoResponse(Long cardId, String cardNumber,
			String cardHolderName, String expiryDate, String billingAddress,
			Instant createdAt, Instant updatedAt) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expiryDate = expiryDate;
		this.billingAddress = billingAddress;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Long getCardId() {
		return cardId;
	}
	
	public void setCardId(Long id) {
		this.cardId = id;
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
