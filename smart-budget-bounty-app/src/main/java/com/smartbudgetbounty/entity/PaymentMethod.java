package com.smartbudgetbounty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaymentMethod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String paymentMethodName;

	public PaymentMethod() {}
	
	public PaymentMethod(String paymentMethodName) {
		super();
		this.paymentMethodName = paymentMethodName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", paymentMethodName="
				+ paymentMethodName + "]";
	}
	
}
