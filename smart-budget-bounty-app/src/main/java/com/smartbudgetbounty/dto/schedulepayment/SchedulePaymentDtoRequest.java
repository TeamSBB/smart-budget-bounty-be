package com.smartbudgetbounty.dto.schedulepayment;

import java.time.Instant;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SchedulePaymentDtoRequest {

	@NotBlank(message = "Recipient name is required")
	private String recipientName;

	@NotBlank(message = "Payment method is required")
	private String paymentMethod;

	// Giro
	private String bankName;

	@Pattern(regexp = "\\d{7,12}", message = "Account number must be 7 to 12 digits")
	private String accountNumber;

	@Positive(message = "Transfer limit must be a positive number")
	private Double transferLimit;

	// Standing Instruction
	private Instant startDate;
	private Instant endDate;
	private String frequency;

	// Credit/Debit Card
	@Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
	private String cardNumber;

	private String nameOnCard;

	@Future(message = "Expiry date must be in the future")
	private Instant expiryDate;

	@Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
	private String cvv;

	@NotNull(message = "User ID is required")
	private Long userId;

	// Getters and Setters...

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
