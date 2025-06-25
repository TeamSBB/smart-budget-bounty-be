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
    private Instant rewardDate;

    @ManyToOne // Owner - Because in a one-many r/s, the many is the owner
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne // Inverse side - Because Transaction exist then can have RewardPoints
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public RewardPointsTransaction() {
        super();
    }

    // constructor for Transaction initiating RewardPointsTransaction
    public RewardPointsTransaction(
        Double amount,
        Instant rewardDate,
        User user,
        Transaction transaction
    ) {
        super();
        this.amount = amount;
        this.rewardDate = rewardDate;
        this.user = user;
        this.transaction = transaction;
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

    public Instant getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(Instant rewardDate) {
        this.rewardDate = rewardDate;
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
}
