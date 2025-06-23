package com.smartbudgetbounty.dto.rewardpointtransaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateRewardPointTransactionDtoRequest {
    @NotNull(message = "Reward Point Transaction amount is required.")
    @Positive(message = "Reward Point Transaction amount must be greater than 0.")
    private Double amount;

    @NotNull(message = "User ID is required.")
    @PositiveOrZero(message = "User ID must be 0 or greater.")
    private Long userId;

    @NotNull(message = "Transaction ID is required.")
    @PositiveOrZero(message = "Transaction ID must be 0 or greater.")
    private Long transactionId;

    public CreateRewardPointTransactionDtoRequest(Double amount, Long userId, Long transactionId) {
        super();
        this.amount = amount;
        this.userId = userId;
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
