package com.smartbudgetbounty.dto.schedulepayment;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateSchedulePaymentDtoRequest {

	@NotBlank(message="billName must not be blank")
	@Size(max = 50, message = "billName must not exceed 50 characters")
	private String billName;
	private Double billAmount;
	private Instant startDate;
	private String recurrence;
	
	public CreateSchedulePaymentDtoRequest(
			String billName,
			Double billAmount, 
			Instant startDate, 
			String recurrence) {
		super();
		this.billName = billName;
		this.billAmount = billAmount;
		this.startDate = startDate;
		this.recurrence = recurrence;
	}
	
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public Instant getStartDate() {
		return startDate;
	}
	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}
	
	
}
