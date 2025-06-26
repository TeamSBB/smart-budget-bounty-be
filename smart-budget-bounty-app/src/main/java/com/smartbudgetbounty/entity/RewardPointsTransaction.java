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

    private Integer amount;
    private Instant pointsTransactionDate;

    // RewardPointsTransaction (owning side) -> User (inverse side)
    // - RewardPointsTransaction holds the foreign key to User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    // RewardPointsTransaction (owning side) -> Transfer (inverse side)
    // - RewardPointsTransaction holds the foreign key to Transfer
    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = true, referencedColumnName = "id")
    private Transfer transfer;

    // RewardPointsTransaction (inverse side) <- RewardVoucher (owning side)
    // - RewardVoucher holds the foreign key to RewardPointsTransaction
    @OneToOne(
        // relationship is mapped by the "pointsTransaction" field in RewardVoucher
        mappedBy = "pointsTransaction",
        // cascade operations from RewardPointsTransaction (parent) to RewardVoucher (child)
        cascade = CascadeType.ALL,
        // delete RewardVoucher (child) if it is removed from RewardPointsTransaction (parent)
        orphanRemoval = true
    )
    private RewardVoucher voucher;

    public RewardPointsTransaction() {
        super();
    }

    public RewardPointsTransaction(
        RewardPointsTransactionType pointsTransactionType,
        Integer amount,
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public RewardVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(RewardVoucher voucher) {
        this.voucher = voucher;
    }

    @Override
    public String toString() {
        return "RewardPointTransaction [id="
            + id
            + ", pointsTransactionType="
            + pointsTransactionType.name()
            + ", amount="
            + amount
            + ", pointsTransactionDate="
            + pointsTransactionDate
            + ", userId="
            + user.getId()
            + ", transferId="
            + (transfer
                != null ? transfer.getId() : null)
            + ", voucherId="
            + (voucher
                != null ? voucher.getId() : null)
            + "]";
    }
}
