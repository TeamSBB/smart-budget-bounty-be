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
	private String recurrence;
	
	@ManyToOne
	@JoinColumn(name = "user_id")  // owning side: FK is here
	private User user;

	//TODO1: Yet to add 1 more r/s to Rewards Table
	
	public SchedulePayment() {}
	
	public SchedulePayment(String billName, Double billAmount,
			Instant startDate, String recurrence, User user) {
		super();
		this.billName = billName;
		this.billAmount = billAmount;
		this.startDate = startDate;
		this.recurrence = recurrence;
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

	

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}

