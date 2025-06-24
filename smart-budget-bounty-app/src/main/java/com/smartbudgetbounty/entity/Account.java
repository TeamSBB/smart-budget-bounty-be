package com.smartbudgetbounty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int accumulatedPoints;
	
	@OneToOne // Owner - Because bank account is a part of AppAccount profile
	@JoinColumn(name="bank_account_id", referencedColumnName="id", unique = true)
	private BankAccount bankAccount;
	
	@OneToOne // Owner - Because credit card is a part of AppAccount profile
	@JoinColumn(name="credit_debit_card_id", referencedColumnName="id", unique = true)
	private CreditDebitCard creditDebitCard;

	@OneToOne // Owner - Because account contains the details of user
	@JoinColumn(name="user_id", referencedColumnName="id", unique = true, nullable = false)
	private User user;	
	
	public Account() {}

	public Account(BankAccount bankAccount, CreditDebitCard creditDebitCard, User user, int accumulatedPoints) {
		super();
		this.bankAccount = bankAccount;
		this.creditDebitCard = creditDebitCard;
		this.user = user;
		this.accumulatedPoints = accumulatedPoints;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public CreditDebitCard getCreditDebitCard() {
		return creditDebitCard;
	}

	public void setCreditDebitCard(CreditDebitCard creditDebitCard) {
		this.creditDebitCard = creditDebitCard;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAccumulatedPoints() {
		return accumulatedPoints;
	}

	public void setAccumulatedPoints(int accumulatedPoints) {
		this.accumulatedPoints = accumulatedPoints;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accumulatedPoints=" + accumulatedPoints
				+ ", bankAccount=" + bankAccount + ", creditDebitCard="
				+ creditDebitCard + ", user=" + user + "]";
	}
	
	
}
