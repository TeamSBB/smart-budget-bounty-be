package com.smartbudgetbounty.enums;

public enum RewardVoucherType {
    MCDONALDS("reward voucher for redemption at McDonald's stores"),
    STARBUCKS("reward voucher for redemption at Starbucks stores");

    private final String description;

    RewardVoucherType(String description) {
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
