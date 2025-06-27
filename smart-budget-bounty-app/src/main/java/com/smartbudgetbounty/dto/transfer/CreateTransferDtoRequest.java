package com.smartbudgetbounty.dto.transfer;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateTransferDtoRequest {
    @NotNull(message = "Transaction amount is required.")
    @Positive(message = "Transaction amount must be greater than 0.")
    private Double transactionAmount;

    @NotNull(message = "User ID is required.")
    @PositiveOrZero(message = "User ID must be 0 or greater.")
    private Long userId;

    private String recipientName;

    private String fromPaynowPhoneNumber;
    private String toPaynowPhoneNumber;

    private String fromAccountNumber;
    private String toAccountNumber;
    private String ccv;

    private String beneficiaryName;
    private String remarks;

    private Instant transferDate;

    public CreateTransferDtoRequest(
            @NotNull(message = "Transaction amount is required.") @Positive(message = "Transaction amount must be greater than 0.") Double transactionAmount,
            @NotNull(message = "User ID is required.") @PositiveOrZero(message = "User ID must be 0 or greater.") Long userId,
            @NotNull(message = "Payment method is required.") Long paymentMethodId,
            String recipientName, String fromPaynowPhoneNumber,
            String toPaynowPhoneNumber, String fromAccountNumber,
            String toAccountNumber, String ccv, String beneficiaryName,
            String remarks, Instant transferDate) {
        super();
        this.transactionAmount = transactionAmount;
        this.userId = userId;
        this.paymentMethodId = paymentMethodId;
        this.recipientName = recipientName;
        this.fromPaynowPhoneNumber = fromPaynowPhoneNumber;
        this.toPaynowPhoneNumber = toPaynowPhoneNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.ccv = ccv;
        this.beneficiaryName = beneficiaryName;
        this.remarks = remarks;
        this.transferDate = transferDate;
    }

    private String recipientName;

    private String fromPaynowPhoneNumber;
    private String toPaynowPhoneNumber;

    private String fromAccountNumber;
    private String toAccountNumber;
    private String ccv;

    private String beneficiaryName;
    private String remarks;

    public Long getPaymentMethodId() {
        return paymentMethodId;
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

    public String getToPaynowPhoneNumber() {
        return toPaynowPhoneNumber;
    }

    public void setToPaynowPhoneNumber(String toPaynowPhoneNumber) {
        this.toPaynowPhoneNumber = toPaynowPhoneNumber;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
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

    @Override
    public String toString() {
        return "CreateTransferDtoRequest [transactionAmount="
                + transactionAmount + ", userId=" + userId
                + ", paymentMethodId=" + paymentMethodId + ", recipientName="
                + recipientName + ", fromPaynowPhoneNumber="
                + fromPaynowPhoneNumber + ", toPaynowPhoneNumber="
                + toPaynowPhoneNumber + ", fromAccountNumber="
                + fromAccountNumber + ", toAccountNumber=" + toAccountNumber
                + ", ccv=" + ccv + ", beneficiaryName=" + beneficiaryName
                + ", remarks=" + remarks + ", transferDate=" + transferDate
                + "]";
    }
}
