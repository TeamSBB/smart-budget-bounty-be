package com.smartbudgetbounty.dto.rewardpointstransaction;

import java.time.Instant;

public class RewardPointsTransactionResponseDto {
    private Long id;
    private String pointsTransactionType;
    private Double amount;
    private Instant pointsTransactionDate;
    private Long userId;
    private Long transactionId;
    private Long rewardVoucherId;

    public RewardPointsTransactionResponseDto(
        Long id,
        Double amount,
        Instant date,
        Long userId,
        Long transactionId
    ) {
        super();
        this.id = id;
        this.amount = amount;
        this.pointsTransactionDate = date;
        this.userId = userId;
        this.transactionId = transactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointsTransactionType() {
        return pointsTransactionType;
    }

    public void setPointsTransactionType(String pointsTransactionType) {
        this.pointsTransactionType = pointsTransactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getPointsTransactionDate() {
        return pointsTransactionDate;
    }

    public void setPointsTransactionDate(Instant pointsTransactionDate) {
        this.pointsTransactionDate = pointsTransactionDate;
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

    public Long getRewardVoucherId() {
        return rewardVoucherId;
    }

    public void setRewardVoucherId(Long rewardVoucherId) {
        this.rewardVoucherId = rewardVoucherId;
    }
}
