package com.smartbudgetbounty.dto.rewardvoucher;

public class RedeemRewardVoucherRequestDto {
    private Long userId;

    public RedeemRewardVoucherRequestDto(Long userId) {
        super();
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
