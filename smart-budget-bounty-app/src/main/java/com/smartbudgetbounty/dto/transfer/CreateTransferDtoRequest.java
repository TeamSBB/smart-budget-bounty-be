package com.smartbudgetbounty.dto.transfer;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateTransferDtoRequest {
	@NotNull(message="Transaction amount is required.")
	@Positive(message = "Transaction amount must be greater than 0.")
	private Double transactionAmount;
	
	@NotNull(message = "User ID is required.")
	@PositiveOrZero(message = "User ID must be 0 or greater.")
	private Long userId;

	@NotNull(message = "Payment method is required.")
	private Long paymentMethodId;

	private String recipientName;
	private String paynowPhoneNumber;
	private String accountNumber;
	private String ccv;
	private String bankName;
	private String beneficiaryName;
	private String remarks;
	
	private Instant transferDate;
	
	public CreateTransferDtoRequest(
			Double transactionAmount,
			Long userId,
			Long paymentMethodId,
			String recipientName, String paynowPhoneNumber,
			String accountNumber, String remarks, String ccv, Instant transferDate, 
			String bankName, String beneficiaryName) {
		super();
		this.transactionAmount = transactionAmount;
		this.userId = userId;
		this.paymentMethodId = paymentMethodId;
		this.recipientName = recipientName;
		this.paynowPhoneNumber = paynowPhoneNumber;
		this.accountNumber = accountNumber;
		this.ccv = ccv;
		this.remarks = remarks;
		this.transferDate = transferDate;
		this.bankName = bankName;
		this.beneficiaryName = beneficiaryName;
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

	public Long getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
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

	public String getPaynowPhoneNumber() {
		return paynowPhoneNumber;
	}

	public void setPaynowPhoneNumber(String paynowPhoneNumber) {
		this.paynowPhoneNumber = paynowPhoneNumber;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	@Override
	public String toString() {
		return "CreateTransferDtoRequest [transactionAmount="
				+ transactionAmount + ", userId=" + userId + ", paymentMethodId="
				+ paymentMethodId + ", recipientName=" + recipientName
				+ ", paynowPhoneNumber=" + paynowPhoneNumber + ", accountNumber="
				+ accountNumber + ", ccv=" + ccv + ", bankName=" + bankName
				+ ", beneficiaryName=" + beneficiaryName + ", remarks="
				+ remarks + ", transferDate=" + transferDate + "]";
	}
	
	
}
