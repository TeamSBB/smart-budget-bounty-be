package com.smartbudgetbounty.entity;

import java.time.Instant;

import com.smartbudgetbounty.enums.RewardVoucherStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class RewardVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RewardVoucherStatus voucherStatus;

    private Double discount;
    private Instant earnDate;
    private Instant redeemDate;

    // RewardVoucher (owning side) -> User (inverse side)
    // - RewardVoucher holds the foreign key to User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    // RewardVoucher (owning side) -> RewardPointsTransaction (inverse side)
    // - RewardVoucher holds the foreign key to RewardPointsTransaction
    @OneToOne
    @JoinColumn(name = "points_transaction_id", nullable = true, referencedColumnName = "id")
    private RewardPointsTransaction pointsTransaction;

    public RewardVoucher() {
        super();
    }

    public RewardVoucher(Double discount, Instant earnDate, User user) {
        super();
        this.voucherStatus = RewardVoucherStatus.AVAILABLE;
        this.discount = discount;
        this.earnDate = earnDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RewardVoucherStatus getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(RewardVoucherStatus voucherStatus) {
        this.voucherStatus = voucherStatus;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RewardPointsTransaction getPointsTransaction() {
        return pointsTransaction;
    }

    public void setPointsTransaction(RewardPointsTransaction pointsTransaction) {
        this.pointsTransaction = pointsTransaction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RewardVoucher) {
            RewardVoucher otherRewardVoucher = (RewardVoucher) obj;
            return otherRewardVoucher.getId() == id;
        }
        return false;
    }

    @Override
    public String toString() {
        return "RewardPointTransaction [id="
            + id
            + ", voucherStatus="
            + voucherStatus.name()
            + ", discount="
            + discount
            + ", earnDate="
            + earnDate
            + ", redeemDate="
            + redeemDate
            + ", userId="
            + user.getId()
            + ", pointsTransactionId="
            + (pointsTransaction != null ? pointsTransaction.getId() : null)
            + "]";
    }
}
