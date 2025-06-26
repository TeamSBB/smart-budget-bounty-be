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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double transactionAmount;
    private Instant createdAt;
    private String recipientName;

    private String paymentMethod;
    private String paynowRecipient;
    private String accountNumber;
    private String remarks;

    private Instant transferDate;

    // Transaction (owning side) -> User (inverse side)
    // - Transaction holds the foreign key to User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Transaction (owning side) -> User (inverse side)
    // - Transaction holds the foreign key to User
    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false, referencedColumnName = "id")
    private PaymentMethod paymentMethod2;

    // Transaction (inverse side) <- RewardPointsTransaction (owning side)
    // - RewardPointsTransaction holds the foreign key to Transaction
    @OneToOne(
        // relationship is mapped by the "transaction" field in RewardVoucher
        mappedBy = "transaction",
        // cascade operations from Transaction (parent) to RewardPointsTransaction (child)
        cascade = CascadeType.ALL,
        // delete RewardPointsTransaction (child) if it is removed from Transaction (parent)
        orphanRemoval = true
    )
    private RewardPointsTransaction pointsTransaction;

//	@OneToOne // Owner - Because Transaction is created for an Account, not the other way around
//	@JoinColumn(name="account_id")
//	private Account account;

    public Transaction() {
        super();
    }

    public Transaction(
        Double transactionAmount,
        Instant createdAt,
        String recipientName,
        String paymentMethod,
        String paynowRecipient,
        String accountNumber,
        String remarks,
        Instant transferDate,
        User user
    ) {
        super();
        this.transactionAmount = transactionAmount;
        this.createdAt = createdAt;
        this.recipientName = recipientName;
        this.paymentMethod = paymentMethod;
        this.paynowRecipient = paynowRecipient;
        this.accountNumber = accountNumber;
        this.remarks = remarks;
        this.transferDate = transferDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaynowRecipient() {
        return paynowRecipient;
    }

    public void setPaynowRecipient(String paynowRecipient) {
        this.paynowRecipient = paynowRecipient;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public PaymentMethod getPaymentMethod2() {
        return paymentMethod2;
    }

    public void setPaymentMethod2(PaymentMethod paymentMethod2) {
        this.paymentMethod2 = paymentMethod2;
    }

    public RewardPointsTransaction getPointsTransaction() {
        return pointsTransaction;
    }

    public void setPointsTransaction(RewardPointsTransaction pointsTransaction) {
        this.pointsTransaction = pointsTransaction;
    }

    @Override
    public String toString() {
        return "Transaction [id="
            + id
            + ", transactionAmount="
            + transactionAmount
            + ", createdAt="
            + createdAt
            + ", recipientName="
            + recipientName
            + ", paymentMethod="
            + paymentMethod
            + ", paynowRecipient="
            + paynowRecipient
            + ", accountNumber="
            + accountNumber
            + ", remarks="
            + remarks
            + ", transferDate="
            + transferDate
            + ", user="
            + user
            + ", paymentMethod2="
            + paymentMethod2
            + "]";
    }

}
