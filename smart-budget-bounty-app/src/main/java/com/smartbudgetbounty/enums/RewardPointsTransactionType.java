package com.smartbudgetbounty.enums;

public enum RewardPointsTransactionType {
    EARNED("Reward points earned from making transactions"),
    REDEEMED("Reward points redeemed for reward vouchers");

    private final String description;

    RewardPointsTransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
