package com.smartbudgetbounty.entity;

import java.time.Instant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double transactionAmount;
    private Instant createdAt;
    private String recipientName;

    private String fromPaynowPhoneNumber;
    private String toPaynowPhoneNumber;

    private String fromAccountNumber;
    private String toAccountNumber;

    private String beneficiaryName;
    private String remarks;

    private Instant transferDate;

    // Transfer (owning side) -> User (inverse side)
    // - Transfer holds the foreign key to User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Transfer (owning side) -> User (inverse side)
    // - Transfer holds the foreign key to PaymentMethod
    @OneToOne
    @JoinColumn(name = "payment_method_id", nullable = true, referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    // Transfer (inverse side) <- RewardPointsTransaction (owning side)
    // - RewardPointsTransaction holds the foreign key to Transfer
    @OneToOne(
            // relationship is mapped by the "transfer" field in RewardVoucher
            mappedBy = "transfer",
            // cascade operations from Transfer (parent) to RewardPointsTransaction (child)
            cascade = CascadeType.ALL,
            // delete RewardPointsTransaction (child) if it is removed from Transfer
            // (parent)
            orphanRemoval = true)
    private RewardPointsTransaction pointsTransaction;

    // @OneToOne // Owner - Because Transaction is created for an Account, not the
    // other way around
    // @JoinColumn(name="account_id")
    // private Account account;

    public Transfer() {
        super();
    }

    public Transfer(Double transactionAmount, Instant createdAt,
            String recipientName, String fromPaynowPhoneNumber,
            String toPaynowPhoneNumber, String fromAccountNumber,
            String toAccountNumber, String beneficiaryName, String remarks,
            Instant transferDate, User user, PaymentMethod paymentMethod) {
        super();
        this.transactionAmount = transactionAmount;
        this.createdAt = createdAt;
        this.recipientName = recipientName;
        this.fromPaynowPhoneNumber = fromPaynowPhoneNumber;
        this.toPaynowPhoneNumber = toPaynowPhoneNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.beneficiaryName = beneficiaryName;
        this.remarks = remarks;
        this.transferDate = transferDate;
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getFromPaynowPhoneNumber() {
        return fromPaynowPhoneNumber;
    }

    public void setFromPaynowPhoneNumber(String fromPaynowPhoneNumber) {
        this.fromPaynowPhoneNumber = fromPaynowPhoneNumber;
    }

    public String getToPaynowPhoneNumber() {
        return toPaynowPhoneNumber;
    }

    public void setToPaynowPhoneNumber(String toPaynowPhoneNumber) {
        this.toPaynowPhoneNumber = toPaynowPhoneNumber;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Instant transferDate) {
        this.transferDate = transferDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Transfer [id=" + id + ", transactionAmount=" + transactionAmount
                + ", createdAt=" + createdAt + ", recipientName="
                + recipientName + ", fromPaynowPhoneNumber="
                + fromPaynowPhoneNumber + ", toPaynowPhoneNumber="
                + toPaynowPhoneNumber + ", fromAccountNumber="
                + fromAccountNumber + ", toAccountNumber=" + toAccountNumber
                + ", beneficiaryName=" + beneficiaryName + ", remarks="
                + remarks + ", transferDate=" + transferDate + ", userId="
                + user.getId()
                + ", paymentMethod="
                + paymentMethod
                + ", pointsTransactionId="
                + pointsTransaction.getId()
                + "]";
    }
}
