package com.smartbudgetbounty.dto.rewardpointtransaction;

import java.time.Instant;

public class RewardPointTransactionDtoResponse {
    private Long id;
    private Double amount;
    private Instant date;
    private Long userId;
    private Long transactionId;

    public RewardPointTransactionDtoResponse(
        Long id,
        Double amount,
        Instant date,
        Long userId,
        Long transactionId
    ) {
        super();
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
        this.transactionId = transactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
