package com.smartbudgetbounty.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CreditDebitCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cardNumber;
	private String cardHolderName;
	private String expiryDate;
	private String billingAddress;
	
	private Instant createdAt;
	private Instant updatedAt;
	
	public CreditDebitCard() {}
	
	public CreditDebitCard(String cardNumber, String cardHolderName,
			String expiryDate, String billingAddress, Instant createdAt,
			Instant updatedAt) {
		super();
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expiryDate = expiryDate;
		this.billingAddress = billingAddress;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "CreditDebitCard [id=" + id + ", cardNumber=" + cardNumber
				+ ", cardHolderName=" + cardHolderName + ", expiryDate="
				+ expiryDate + ", billingAddress=" + billingAddress
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
}
