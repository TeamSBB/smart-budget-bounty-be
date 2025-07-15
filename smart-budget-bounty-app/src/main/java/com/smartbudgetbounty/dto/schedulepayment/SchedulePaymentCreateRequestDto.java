package com.smartbudgetbounty.dto.schedulepayment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.Instant;

public class SchedulePaymentCreateRequestDto {

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String accountId;

    private String bankName;
    private String bankCode;
    private String branchCode;
    private String recipientAccountNumber;
    private String accountHolderName;
    private String billingOrganizationCode;

    @Positive
    private Double giroAmount;

    private String giroFrequency;

    //	@FutureOrPresent
    private Instant giroStartDate;

    private String recipientName;
    private String standingRecipientAccountNumber;

    @Positive
    private Double standingAmount;

    private String frequency;

    //	@FutureOrPresent
    private Instant startDate;

    //	@Future
    private Instant endDate;

    public SchedulePaymentCreateRequestDto() {
    }

    // Getters and Setters
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBillingOrganizationCode() {
        return billingOrganizationCode;
    }

    public void setBillingOrganizationCode(String billingOrganizationCode) {
        this.billingOrganizationCode = billingOrganizationCode;
    }

    public Double getGiroAmount() {
        return giroAmount;
    }

    public void setGiroAmount(Double giroAmount) {
        this.giroAmount = giroAmount;
    }

    public String getGiroFrequency() {
        return giroFrequency;
    }

    public void setGiroFrequency(String giroFrequency) {
        this.giroFrequency = giroFrequency;
    }

    public Instant getGiroStartDate() {
        return giroStartDate;
    }

    public void setGiroStartDate(Instant giroStartDate) {
        this.giroStartDate = giroStartDate;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getStandingRecipientAccountNumber() {
        return standingRecipientAccountNumber;
    }

    public void setStandingRecipientAccountNumber(String standingRecipientAccountNumber) {
        this.standingRecipientAccountNumber = standingRecipientAccountNumber;
    }

    public Double getStandingAmount() {
        return standingAmount;
    }

    public void setStandingAmount(Double standingAmount) {
        this.standingAmount = standingAmount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
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
}