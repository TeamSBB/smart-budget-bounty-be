package com.smartbudgetbounty.dto.rewardpointstransaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateEarnRewardPointsTransactionRequestDto {
    @NotNull(message = "Reward Points Transaction amount is required.")
    @Positive(message = "Reward Points Transaction amount must be greater than 0.")
    private Double amount;

    @NotNull(message = "Transaction ID is required.")
    @Positive(message = "Transaction ID must be greater than 0.")
    private Long transactionId;

    public CreateEarnRewardPointsTransactionRequestDto(Double amount, Long transactionId) {
        super();
        this.amount = amount;
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
