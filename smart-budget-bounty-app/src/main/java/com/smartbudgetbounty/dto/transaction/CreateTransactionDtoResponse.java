package com.smartbudgetbounty.dto.transaction;

import java.time.Instant;

public class CreateTransactionDtoResponse {
	private Long transactionId;
	private Double transactionAmount;
	private String recipientName;
	private Instant createdAt;
	private Instant transferDate;
	private String paymentMethod;
	private String paynowRecipient;
	private String accountNumber;
	private String remarks;
	
	public CreateTransactionDtoResponse(Long transactionId,
			Double transactionAmount, String recipientName, Instant createdAt,
			Instant transferDate,
			String paymentMethod, String paynowRecipient,
			String accountNumber, String remarks) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
		this.recipientName = recipientName;
		this.createdAt = createdAt;
		this.transferDate = transferDate;
		this.paymentMethod = paymentMethod;
		this.paynowRecipient = paynowRecipient;
		this.accountNumber = accountNumber;
		this.remarks = remarks;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
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
