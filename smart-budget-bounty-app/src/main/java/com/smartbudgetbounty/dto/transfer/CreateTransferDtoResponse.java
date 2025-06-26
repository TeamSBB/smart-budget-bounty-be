package com.smartbudgetbounty.dto.transfer;

import java.time.Instant;

public class CreateTransferDtoResponse {
	private Long transferId;

	// Card
	private String recipientName;
	
	// Paynow
	private String paynowPhoneNumber;
	
	// Bank
	private Instant transferDate;
	private String bankName;
	private String beneficiaryName;
	
	// Card and Bank
	private String accountNumber;
	
	// Generic
	private Double transactionAmount;
	private Instant createdAt;
	private Long paymentMethodId;
	private String remarks;
	
	public CreateTransferDtoResponse(Long transferId,
			Double transactionAmount, String recipientName, Long paymentMethodId, 
			Instant createdAt, Instant transferDate, String paynowPhoneNumber,
			String accountNumber, String remarks, String bankName,
			String beneficiaryName) {
		super();
		this.transferId = transferId;
		this.transactionAmount = transactionAmount;
		this.recipientName = recipientName;
		this.createdAt = createdAt;
		this.transferDate = transferDate;
		this.paymentMethodId = paymentMethodId;
		this.paynowPhoneNumber = paynowPhoneNumber;
		this.accountNumber = accountNumber;
		this.remarks = remarks;
		this.bankName = bankName;
		this.beneficiaryName = beneficiaryName;
	}
	public Long getTransferId() {
		return transferId;
	}
	public void setTransferId(Long transferId) {
		this.transferId = transferId;
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
	public Long getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	
	public String getPaynowPhoneNumber() {
		return paynowPhoneNumber;
	}
	public void setPaynowPhoneNumber(String paynowPhoneNumber) {
		this.paynowPhoneNumber = paynowPhoneNumber;
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
		return "CreateTransferDtoResponse [transferId=" + transferId
				+ ", recipientName=" + recipientName + ", paynowPhoneNumber="
				+ paynowPhoneNumber + ", transferDate=" + transferDate
				+ ", bankName=" + bankName + ", beneficiaryName="
				+ beneficiaryName + ", accountNumber=" + accountNumber
				+ ", transactionAmount=" + transactionAmount + ", createdAt="
				+ createdAt + ", paymentMethodId=" + paymentMethodId + ", remarks="
				+ remarks + "]";
	}
	
}
