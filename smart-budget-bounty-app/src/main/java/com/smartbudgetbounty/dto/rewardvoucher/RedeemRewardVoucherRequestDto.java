package com.smartbudgetbounty.dto.rewardvoucher;

public class RedeemRewardVoucherRequestDto {
    private Long voucherId;

    public RedeemRewardVoucherRequestDto(Long voucherId) {
        super();
        this.voucherId = voucherId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }
}
