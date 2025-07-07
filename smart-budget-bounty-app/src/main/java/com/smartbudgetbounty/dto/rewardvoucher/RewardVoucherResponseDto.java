package com.smartbudgetbounty.dto.rewardvoucher;

import java.time.Instant;

public class RewardVoucherResponseDto {
    private Long id;
    private String voucherStatus;
    private String voucherType;
    private Double discount;
    private Instant earnDate;
    private Instant redeemDate;
    private Long userId;
    private Long pointsTransactionId;

    public RewardVoucherResponseDto() {
        super();
    }

    public RewardVoucherResponseDto(
        Long id,
        String voucherStatus,
        String voucherType,
        Double discount,
        Instant earnDate,
        Instant redeemDate,
        Long userId,
        Long pointsTransactionId
    ) {
        super();
        this.id = id;
        this.voucherStatus = voucherStatus;
        this.voucherType = voucherType;
        this.discount = discount;
        this.earnDate = earnDate;
        this.redeemDate = redeemDate;
        this.userId = userId;
        this.pointsTransactionId = pointsTransactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(String voucherStatus) {
        this.voucherStatus = voucherStatus;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Instant getEarnDate() {
        return earnDate;
    }

    public void setEarnDate(Instant earnDate) {
        this.earnDate = earnDate;
    }

    public Instant getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(Instant redeemDate) {
        this.redeemDate = redeemDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPointsTransactionId() {
        return pointsTransactionId;
    }

    public void setPointsTransactionId(Long pointsTransactionId) {
        this.pointsTransactionId = pointsTransactionId;
    }
}
