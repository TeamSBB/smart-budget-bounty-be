package com.smartbudgetbounty.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class RewardVoucherTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double discount;
    private Instant rewardTransactionDate;

    @ManyToOne // Owner - Because in a one-many r/s, the many is the owner
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne // Inverse side - Because RewardPointsTransaction exist then can have RewardVouchers
    @JoinColumn(name = "reward_points_transaction_id")
    private RewardPointsTransaction rewardPointsTransaction;

    public RewardVoucherTransaction() {
        super();
    }

    // constructor for RewardPointsTransaction initiating RewardVoucherTransaction
    public RewardVoucherTransaction(
        Double discount,
        Instant rewardTransactionDate,
        User user,
        RewardPointsTransaction rewardPointsTransaction
    ) {
        super();
        this.discount = discount;
        this.rewardTransactionDate = rewardTransactionDate;
        this.user = user;
        this.rewardPointsTransaction = rewardPointsTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Instant getRewardTransactionDate() {
        return rewardTransactionDate;
    }

    public void setRewardTransactionDate(Instant rewardTransactionDate) {
        this.rewardTransactionDate = rewardTransactionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RewardPointsTransaction getRewardPointsTransaction() {
        return rewardPointsTransaction;
    }

    public void setRewardPointsTransaction(RewardPointsTransaction rewardPointsTransaction) {
        this.rewardPointsTransaction = rewardPointsTransaction;
    }
}
