package com.smartbudgetbounty.enums;

public enum RewardVoucherStatus {
    AVAILABLE("Reward voucher available to be redeemed for partner benefits"),
    REDEEMED("Reward voucher redeemed for partner benefits");

    private final String description;

    RewardVoucherStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
