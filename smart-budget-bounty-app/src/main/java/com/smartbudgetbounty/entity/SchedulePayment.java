package com.smartbudgetbounty.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "schedule_payments")
public class SchedulePayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String paymentMethod;
	private String accountId;

	// Giro fields
	private String bankName;
	private String bankCode;
	private String branchCode;
	private String recipientAccountNumber;
	private String accountHolderName;
	private String billingOrganizationCode;
	private Double giroAmount;
	private String giroFrequency;
	private Instant giroStartDate;

	// Standing Instruction fields
	private String recipientName;
	private String standingRecipientAccountNumber;
	private Double standingAmount;
	private String frequency;
	private Instant startDate;
	private Instant endDate;

	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id") // owning side: FK is here
	private User user;

	// TODO1: Yet to add 1 more r/s to Rewards Table

	public SchedulePayment() {
	}

	public SchedulePayment(Long id, String paymentMethod, String bankName, String bankCode, String branchCode,
			String recipientAccountNumber, String accountHolderName, String billingOrganizationCode, Double giroAmount,
			String giroFrequency, Instant giroStartDate, String recipientName, String standingRecipientAccountNumber,
			Double standingAmount, String frequency, Instant startDate, Instant endDate, User user) {
		super();
		this.id = id;
		this.paymentMethod = paymentMethod;
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.branchCode = branchCode;
		this.recipientAccountNumber = recipientAccountNumber;
		this.accountHolderName = accountHolderName;
		this.billingOrganizationCode = billingOrganizationCode;
		this.giroAmount = giroAmount;
		this.giroFrequency = giroFrequency;
		this.giroStartDate = giroStartDate;
		this.recipientName = recipientName;
		this.standingRecipientAccountNumber = standingRecipientAccountNumber;
		this.standingAmount = standingAmount;
		this.frequency = frequency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
