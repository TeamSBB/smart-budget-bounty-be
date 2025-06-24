package com.smartbudgetbounty.entity;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SchedulePayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Common fields
	private String recipientName;
	private String paymentMethod;
	
	// Giro
	private String bankName;
	private String accountNumber;
	private Double transferLimit;
	
	// Standing Instruction
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Singapore")
	private Instant startDate;
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Singapore")
	private Instant endDate;
	
	private String frequency;
	
	// Credit/Debit Card
	private String cardNumber;
	private String nameOnCard;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Singapore")
	private Instant expiryDate;
	
	private String cvv;
	
	@ManyToOne
	@JoinColumn(name = "user_id")  // owning side: FK is here
	private User user;

	//TODO1: Yet to add 1 more r/s to Rewards Table
	
	public SchedulePayment() {}

	public SchedulePayment(Long id, String recipientName, String paymentMethod, String bankName,
			String accountNumber, Double transferLimit, Instant startDate, Instant endDate, String frequency,
			String cardNumber, String nameOnCard, Instant expiryDate, String cvv, User user) {
		super();
		this.id = id;
		this.recipientName = recipientName;
		this.paymentMethod = paymentMethod;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.transferLimit = transferLimit;
		this.startDate = startDate;
		this.endDate = endDate;
		this.frequency = frequency;
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getTransferLimit() {
		return transferLimit;
	}

	public void setTransferLimit(Double transferLimit) {
		this.transferLimit = transferLimit;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

