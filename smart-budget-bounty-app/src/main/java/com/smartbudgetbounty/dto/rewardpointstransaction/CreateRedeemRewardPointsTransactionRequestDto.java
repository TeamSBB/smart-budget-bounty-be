package com.smartbudgetbounty.dto.rewardpointstransaction;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;

public class CreateRedeemRewardPointsTransactionRequestDto {
    @NotNull(message = "Reward Points Transaction amount is required.")
    @Negative(message = "Reward Points Transaction amount must be less than 0.")
    private Double amount;

    public CreateRedeemRewardPointsTransactionRequestDto(Double amount) {
        super();
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
