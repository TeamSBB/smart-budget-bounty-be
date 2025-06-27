package com.smartbudgetbounty.dto.rewardpointstransaction;

public class CreateRedeemRewardPointsTransactionRequestDto {
    private Integer redeemAmount;

    public CreateRedeemRewardPointsTransactionRequestDto(Integer redeemAmount) {
        super();
        this.redeemAmount = redeemAmount;
    }

    public Integer getRedeemAmount() {
        return redeemAmount;
    }

    public void setRedeemAmount(Integer redeemAmount) {
        this.redeemAmount = redeemAmount;
    }
}
