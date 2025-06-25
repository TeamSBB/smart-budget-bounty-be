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
public class RewardPointsTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Instant rewardTransactionDate;

    @ManyToOne // Owner - Because in a one-many r/s, the many is the owner
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne // Inverse side - Because Transaction exist then can have RewardPoints
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @OneToOne // Owner - Because RewardPointsTransaction exist then can have RewardVouchers
    @JoinColumn(name = "reward_voucher_id")
    private RewardVoucherTransaction rewardVoucherTransaction;

    public RewardPointsTransaction() {
        super();
    }

    // constructor for Transaction initiating RewardPointsTransaction
    public RewardPointsTransaction(
        Double amount,
        Instant rewardTransactionDate,
        User user,
        Transaction transaction
    ) {
        super();
        this.amount = amount;
        this.rewardTransactionDate = rewardTransactionDate;
        this.user = user;
        this.transaction = transaction;
    }

    // constructor for RewardPointsTransaction initiating RewardVoucherTransaction
    public RewardPointsTransaction(Double amount, Instant rewardTransactionDate, User user) {
        super();
        this.amount = amount;
        this.rewardTransactionDate = rewardTransactionDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public RewardVoucherTransaction getRewardVoucherTransaction() {
        return rewardVoucherTransaction;
    }

    public void setRewardVoucherTransaction(RewardVoucherTransaction rewardVoucherTransaction) {
        this.rewardVoucherTransaction = rewardVoucherTransaction;
    }
}
