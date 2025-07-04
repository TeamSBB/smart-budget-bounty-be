package com.smartbudgetbounty.dto.transfer;

import java.time.Instant;

public class TransferResponseDto {
    private Long transferId;

    // Card
    private String recipientName;

    // Paynow
    private String fromPaynowPhoneNumber;
    private String toPaynowPhoneNumber;

    // Bank
    private Instant transferDate;
    private String beneficiaryName;

    // Card and Bank
    private String fromAccountNumber;
    private String toAccountNumber;

    // Generic
    private Double amount;
    private Instant createdAt;
    private Long paymentMethodId;
    private String remarks;
    
    // Rewards
    private Integer rewardAmount;

    public TransferResponseDto(
        Long transferId,
        String recipientName,
        String fromPaynowPhoneNumber,
        String toPaynowPhoneNumber,
        Instant transferDate,
        String beneficiaryName,
        String fromAccountNumber,
        String toAccountNumber,
        Double amount,
        Instant createdAt,
        Long paymentMethodId,
        String remarks,
        Integer rewardAmount
    ) {
        super();
        this.transferId = transferId;
        this.recipientName = recipientName;
        this.fromPaynowPhoneNumber = fromPaynowPhoneNumber;
        this.toPaynowPhoneNumber = toPaynowPhoneNumber;
        this.transferDate = transferDate;
        this.beneficiaryName = beneficiaryName;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.createdAt = createdAt;
        this.paymentMethodId = paymentMethodId;
        this.remarks = remarks;
        this.rewardAmount = rewardAmount;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getFromPaynowPhoneNumber() {
        return fromPaynowPhoneNumber;
    }

    public void setFromPaynowPhoneNumber(String fromPaynowPhoneNumber) {
        this.fromPaynowPhoneNumber = fromPaynowPhoneNumber;
    }

    public String getToPaynowPhoneNumber() {
        return toPaynowPhoneNumber;
    }

    public void setToPaynowPhoneNumber(String toPaynowPhoneNumber) {
        this.toPaynowPhoneNumber = toPaynowPhoneNumber;
    }

    public Instant getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Instant transferDate) {
        this.transferDate = transferDate;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public Integer getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Integer rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	@Override
	public String toString() {
		return "TransferResponseDto [transferId=" + transferId
				+ ", recipientName=" + recipientName
				+ ", fromPaynowPhoneNumber=" + fromPaynowPhoneNumber
				+ ", toPaynowPhoneNumber=" + toPaynowPhoneNumber
				+ ", transferDate=" + transferDate + ", beneficiaryName="
				+ beneficiaryName + ", fromAccountNumber=" + fromAccountNumber
				+ ", toAccountNumber=" + toAccountNumber + ", amount=" + amount
				+ ", createdAt=" + createdAt + ", paymentMethodId="
				+ paymentMethodId + ", remarks=" + remarks + ", rewardAmount="
				+ rewardAmount + "]";
	}
}
