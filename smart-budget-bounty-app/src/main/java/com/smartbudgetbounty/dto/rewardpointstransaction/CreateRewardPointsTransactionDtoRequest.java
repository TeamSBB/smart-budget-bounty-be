package com.smartbudgetbounty.dto.rewardpointstransaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateRewardPointsTransactionDtoRequest {
    @NotNull(message = "Reward Point Transaction amount is required.")
    @Positive(message = "Reward Point Transaction amount must be greater than 0.")
    private Double amount;

    @NotNull(message = "Transaction ID is required.")
    @PositiveOrZero(message = "Transaction ID must be 0 or greater.")
    private Long transactionId;

    public CreateRewardPointsTransactionDtoRequest(Double amount, Long transactionId) {
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
