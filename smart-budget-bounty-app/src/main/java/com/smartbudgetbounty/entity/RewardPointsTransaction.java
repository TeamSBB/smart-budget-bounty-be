package com.smartbudgetbounty.entity;

import java.time.Instant;

import com.smartbudgetbounty.enums.RewardPointsTransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class RewardPointsTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RewardPointsTransactionType pointsTransactionType;
    private Double amount;
    private Instant pointsTransactionDate;

    @ManyToOne // Owner - Because in a one-many r/s, the many is the owner
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne // Inverse side - Because Transaction exist then can have RewardPoints
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @OneToOne // Owner - Because RewardPointsTransaction exist then can have RewardVouchers
    @JoinColumn(name = "reward_voucher_id")
    private RewardVoucher rewardVoucher;

    public RewardPointsTransaction() {
        super();
    }

    // constructor for earning reward points from making a transaction
    // - rewardVoucher is null
    public RewardPointsTransaction(
        Double amount,
        Instant pointsTransactionDate,
        User user,
        Transaction transaction
    ) {
        super();
        this.pointsTransactionType = RewardPointsTransactionType.EARNED;
        this.amount = amount;
        this.pointsTransactionDate = pointsTransactionDate;
        this.user = user;
        this.transaction = transaction;
    }

    // constructor for redeeming reward points to earn a reward voucher
    // - transaction is null, rewardVoucher will be assigned later
    public RewardPointsTransaction(Double amount, Instant pointsTransactionDate, User user) {
        super();
        this.pointsTransactionType = RewardPointsTransactionType.REDEEMED;
        this.amount = amount;
        this.pointsTransactionDate = pointsTransactionDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RewardPointsTransactionType getPointsTransactionType() {
        return pointsTransactionType;
    }

    public void setPointsTransactionType(RewardPointsTransactionType pointsTransactionType) {
        this.pointsTransactionType = pointsTransactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getPointsTransactionDate() {
        return pointsTransactionDate;
    }

    public void setPointsTransactionDate(Instant pointsTransactionDate) {
        this.pointsTransactionDate = pointsTransactionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public RewardVoucher getRewardVoucher() {
        return rewardVoucher;
    }

    public void setRewardVoucher(RewardVoucher rewardVoucher) {
        this.rewardVoucher = rewardVoucher;
    }
}
