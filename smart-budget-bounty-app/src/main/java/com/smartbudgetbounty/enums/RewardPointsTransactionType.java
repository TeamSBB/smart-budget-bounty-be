package com.smartbudgetbounty.enums;

public enum RewardPointsTransactionType {
    EARN("Adds reward points earned from making transactions"),
    REDEEM("Deducts reward points used to redeem reward vouchers");

    private final String description;

    RewardPointsTransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name() + " - " + description;
    }
}
