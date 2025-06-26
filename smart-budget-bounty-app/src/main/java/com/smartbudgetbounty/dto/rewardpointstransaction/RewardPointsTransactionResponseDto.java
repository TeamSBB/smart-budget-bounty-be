package com.smartbudgetbounty.dto.rewardpointstransaction;

import java.time.Instant;

public class RewardPointsTransactionResponseDto {
    private Long id;
    private String pointsTransactionType;
    private Integer amount;
    private Instant pointsTransactionDate;
    private Long userId;
    private Long transactionId;
    private Long voucherId;

    public RewardPointsTransactionResponseDto(
        Long id,
        String pointsTransactionType,
        Integer amount,
        Instant pointsTransactionDate,
        Long userId,
        Long transactionId,
        Long voucherId
    ) {
        super();
        this.id = id;
        this.pointsTransactionType = pointsTransactionType;
        this.amount = amount;
        this.pointsTransactionDate = pointsTransactionDate;
        this.userId = userId;
        this.transactionId = transactionId;
        this.voucherId = voucherId;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }
}
