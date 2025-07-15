package com.smartbudgetbounty.dto.schedulepayment;

import java.time.Instant;

public class SchedulePaymentResponseDto {

    private Long id;
    private String recipientName;
    private String recipientAccountNumber;
    private String paymentMethod;
    private Double amount;
    private String frequency;
    private Instant startDate;
    private Instant endDate;
    private String status;

    public SchedulePaymentResponseDto() {
    }

    public SchedulePaymentResponseDto(String recipientName, String recipientAccountNumber, String paymentMethod, Double amount, String frequency, Instant startDate, Instant endDate, String status) {
        this.recipientName = recipientName;
        this.recipientAccountNumber = recipientAccountNumber;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
}