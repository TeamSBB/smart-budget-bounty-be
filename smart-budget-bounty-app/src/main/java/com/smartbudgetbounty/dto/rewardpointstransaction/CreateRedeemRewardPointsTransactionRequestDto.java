package com.smartbudgetbounty.dto.rewardpointstransaction;

public class CreateRedeemRewardPointsTransactionRequestDto {
    private String voucherType;
    private Integer redeemAmount;

    public CreateRedeemRewardPointsTransactionRequestDto(String voucherType, Integer redeemAmount) {
        super();
        this.voucherType = voucherType;
        this.redeemAmount = redeemAmount;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public Integer getRedeemAmount() {
        return redeemAmount;
    }

    public void setRedeemAmount(Integer redeemAmount) {
        this.redeemAmount = redeemAmount;
    }
}
