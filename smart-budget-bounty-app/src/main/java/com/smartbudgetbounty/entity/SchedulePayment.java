package com.smartbudgetbounty.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SchedulePayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String billName;
	private Double billAmount;
	private Instant startDate;
	private boolean recurringFlag;
	
	@ManyToOne
	@JoinColumn(name = "user_id")  // owning side: FK is here
	private User user;

	//TODO1: Yet to add 1 more r/s to Rewards Table
	
	public SchedulePayment() {}
	
	public SchedulePayment(String billName, Double billAmount,
			Instant startDate, boolean recurringFlag, User user) {
		super();
		this.billName = billName;
		this.billAmount = billAmount;
		this.startDate = startDate;
		this.recurringFlag = recurringFlag;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isRecurringFlag() {
		return recurringFlag;
	}

	public void setRecurringFlag(boolean recurringFlag) {
		this.recurringFlag = recurringFlag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}

