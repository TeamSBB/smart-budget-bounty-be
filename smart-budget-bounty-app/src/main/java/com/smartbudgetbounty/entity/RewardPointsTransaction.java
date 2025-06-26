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

    @OneToOne // Inverse side - Because Transaction exist then can have rewards
    @JoinColumn(name = "transaction_id")
    private Transfer transaction;

    public RewardPointsTransaction() {
        super();
    }

    public RewardPointsTransaction(Double amount, Instant date, User user, Transfer transaction) {
        super();
        this.amount = amount;
        this.rewardDate = date;
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

    public Instant getDate() {
        return rewardDate;
    }

    public void setDate(Instant date) {
        this.rewardDate = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transfer getTransaction() {
        return transaction;
    }

    public void setTransaction(Transfer transaction) {
        this.transaction = transaction;
    }
}
