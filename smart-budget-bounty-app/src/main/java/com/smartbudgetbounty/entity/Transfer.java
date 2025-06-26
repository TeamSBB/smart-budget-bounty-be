package com.smartbudgetbounty.entity;

import java.time.Instant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double transactionAmount;
    private Instant createdAt;
    private String recipientName;

    private String paynowPhoneNumber;
    private String accountNumber;
    private String bankName;
    private String beneficiaryName;
    private String remarks;

    private Instant transferDate;

    // Transfer (owning side) -> User (inverse side)
    // - Transfer holds the foreign key to User
    @JoinColumn(name = "user_id")
    private User user;

    // Transfer (owning side) -> User (inverse side)
    // - Transfer holds the foreign key to PaymentMethod
    @JoinColumn(name = "payment_method_id", nullable = true, referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    // Transfer (inverse side) <- RewardPointsTransaction (owning side)
    // - RewardPointsTransaction holds the foreign key to Transfer
    @OneToOne(
        // relationship is mapped by the "transfer" field in RewardVoucher
        mappedBy = "transfer",
        // cascade operations from Transfer (parent) to RewardPointsTransaction (child)
        cascade = CascadeType.ALL,
        // delete RewardPointsTransaction (child) if it is removed from Transfer (parent)
        orphanRemoval = true
    )
    private RewardPointsTransaction pointsTransaction;

//	@OneToOne // Owner - Because Transaction is created for an Account, not the other way around
//	@JoinColumn(name="account_id")
//	private Account account;

    public Transfer() {
        super();
    }

    public Transfer(
        Double transactionAmount,
        Instant createdAt,
        String recipientName,
        PaymentMethod paymentMethod,
        String paynowPhoneNumber,
        String accountNumber,
        String remarks,
        String bankName,
        String beneficiaryName,
        Instant transferDate,
        User user
    ) {
        super();
        this.transactionAmount = transactionAmount;
        this.createdAt = createdAt;
        this.recipientName = recipientName;
        this.paymentMethod = paymentMethod;
        this.paynowPhoneNumber = paynowPhoneNumber;
        this.accountNumber = accountNumber;
        this.remarks = remarks;
        this.transferDate = transferDate;
        this.user = user;
        this.bankName = bankName;
        this.beneficiaryName = beneficiaryName;
        this.paymentMethod = paymentMethod;
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

    public String getPaynowPhoneNumber() {
        return paynowPhoneNumber;
    }

    public void setPaynowPhoneNumber(String paynowPhoneNumber) {
        this.paynowPhoneNumber = paynowPhoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public RewardPointsTransaction getPointsTransaction() {
        return pointsTransaction;
    }

    public void setPointsTransaction(RewardPointsTransaction pointsTransaction) {
        this.pointsTransaction = pointsTransaction;
    }

    @Override
    public String toString() {
        return "Transfer [id="
            + id
            + ", transactionAmount="
            + transactionAmount
            + ", createdAt="
            + createdAt
            + ", recipientName="
            + recipientName
            + ", paynowPhoneNumber="
            + paynowPhoneNumber
            + ", accountNumber="
            + accountNumber
            + ", bankName="
            + bankName
            + ", beneficiaryName="
            + beneficiaryName
            + ", remarks="
            + remarks
            + ", transferDate="
            + transferDate
            + ", user="
            + user
            + ", paymentMethod="
            + paymentMethod
            + "]";
    }
}
