package com.smartbudgetbounty.entity;

import java.time.Instant;

import com.smartbudgetbounty.enums.RewardPointsTransactionType;

import jakarta.persistence.CascadeType;
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
public class RewardPointsTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RewardPointsTransactionType pointsTransactionType;

    private Double amount;
    private Instant pointsTransactionDate;

    // RewardPointsTransaction (owning side) -> User (inverse side)
    // - RewardPointsTransaction holds the foreign key to User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // RewardPointsTransaction (owning side) -> Transaction (inverse side)
    // - RewardPointsTransaction holds the foreign key to Transaction
    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    // RewardPointsTransaction (inverse side) <- RewardVoucher (owning side)
    // - RewardVoucher holds the foreign key to RewardPointsTransaction
    @OneToOne(
        // relationship is mapped by the "reward_points_transaction" field in RewardVoucher
        mappedBy = "reward_points_transaction",
        // cascade operations from RewardPointsTransaction (parent) to RewardVoucher (child)
        cascade = CascadeType.ALL,
        // delete RewardVoucher (child) if it is removed from RewardPointsTransaction (parent)
        orphanRemoval = true
    )
    private RewardVoucher rewardVoucher;

    public RewardPointsTransaction() {
        super();
    }

    public RewardPointsTransaction(
        RewardPointsTransactionType pointsTransactionType,
        Double amount,
        Instant pointsTransactionDate,
        User user
    ) {
        super();
        this.pointsTransactionType = pointsTransactionType;
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
