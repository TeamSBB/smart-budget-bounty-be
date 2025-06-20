package com.smartbudgetbounty.dto.transaction;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateTransactionDtoRequest {
	@NotNull(message="Transaction amount is required.")
	@Positive(message = "Transaction amount must be greater than 0.")
	private Double transactionAmount;
	
	@NotNull(message = "User ID is required.")
	@PositiveOrZero(message = "User ID must be 0 or greater.")
	private Long userId;

	@NotNull(message = "Payment method is required.")
	private String paymentMethod;

	private String recipientName;
	private String paynowRecipient;
	private String accountNumber;
	private String remarks;
	
	private Instant transferDate;

	public CreateTransactionDtoRequest(
			Double transactionAmount,
			Long userId,
			String paymentMethod,
			String recipientName, String paynowRecipient,
			String accountNumber, String remarks, Instant transferDate) {
		super();
		this.transactionAmount = transactionAmount;
		this.userId = userId;
		this.paymentMethod = paymentMethod;
		this.recipientName = recipientName;
		this.paynowRecipient = paynowRecipient;
		this.accountNumber = accountNumber;
		this.remarks = remarks;
		this.transferDate = transferDate;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Instant getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Instant transferDate) {
		this.transferDate = transferDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPaynowRecipient() {
		return paynowRecipient;
	}

	public void setPaynowRecipient(String paynowRecipient) {
		this.paynowRecipient = paynowRecipient;
	}

}
