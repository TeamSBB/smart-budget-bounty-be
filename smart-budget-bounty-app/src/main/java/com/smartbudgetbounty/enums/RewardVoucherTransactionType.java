package com.smartbudgetbounty.enums;

public enum RewardVoucherTransactionType {
    EARNED("Reward voucher earned from redeeming reward points"),
    REDEEMED("Reward voucher redeemed for partner benefits");

    private final String description;

    RewardVoucherTransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
