package com.smartbudgetbounty.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double transactionAmount;
	private Instant createdAt;
	private String recipientName;
	
	private String paymentMethod;
	private String paynowRecipient;
	private String accountNumber;
	private String remarks;
	
	private Instant transferDate;
	
	@ManyToOne // Owner - Because in a one-many r/s, the many is the owner
	@JoinColumn(name = "user_id")
	private User user;
	
//	@OneToOne // Owner - Because Transaction exist then can have rewards
//	@JoinColumn(name="reward_id")
//	private Reward reward;
	
//	@OneToOne // Owner - Because Transaction is created for an Account, not the other way around
//	@JoinColumn(name="account_id")
//	private Account account;

	public Transaction(Double transactionAmount, Instant createdAt,
		String recipientName, String paymentMethod, String paynowRecipient, 
		String accountNumber, String remarks,
		Instant transferDate, User user) {
		super();
		this.transactionAmount = transactionAmount;
		this.createdAt = createdAt;
		this.recipientName = recipientName;
		this.paymentMethod = paymentMethod;
		this.paynowRecipient = paynowRecipient;
		this.accountNumber = accountNumber;
		this.remarks = remarks;
		this.transferDate = transferDate;
		this.user = user;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getPaynowRecipient() {
		return paynowRecipient;
	}


	public void setPaynowRecipient(String paynowRecipient) {
		this.paynowRecipient = paynowRecipient;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Instant getTransferDate() {
		return transferDate;
	}


	public void setTransferDate(Instant transferDate) {
		this.transferDate = transferDate;
	}
	
	
}
