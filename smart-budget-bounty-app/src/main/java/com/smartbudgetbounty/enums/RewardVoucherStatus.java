package com.smartbudgetbounty.enums;

public enum RewardVoucherStatus {
    AVAILABLE("reward voucher available to be redeemed for partner benefits"),
    REDEEMED("reward voucher redeemed for partner benefits");

    private final String description;

    RewardVoucherStatus(String description) {
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
