package com.smartbudgetbounty.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"") // Use escaped quotes to tell DB to treat it literally
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String address;
    private String contactNumber;
    private String firstName;
    private String lastName;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant lastLogin;

    @OneToMany(mappedBy = "user") // inverse side
    private List<SchedulePayment> schedulePayments = new ArrayList<>();

    @OneToMany(mappedBy = "user") // inverse side
    private List<Transfer> transfers = new ArrayList<>();

    @OneToMany(mappedBy = "user") // inverse side
    private List<RewardPointsTransaction> pointsTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "user") // inverse side
    private List<RewardVoucher> vouchers = new ArrayList<>();

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        createdAt = lastLogin = Instant.now();
    }

    public User(
        String username,
        String password,
        String address,
        String contactNumber,
        String email,
        String firstName,
        String lastName
    ) {
        super();
        this.username = username;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        createdAt = lastLogin = updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<SchedulePayment> getSchedulePayments() {
        return schedulePayments;
    }

    public void setSchedulePayments(List<SchedulePayment> schedulePayments) {
        this.schedulePayments = schedulePayments;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public List<RewardPointsTransaction> getPointsTransactions() {
        return pointsTransactions;
    }

    public void setPointsTransactions(List<RewardPointsTransaction> pointsTransactions) {
        this.pointsTransactions = pointsTransactions;
    }

    public List<RewardVoucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<RewardVoucher> vouchers) {
        this.vouchers = vouchers;
    }

    @Override
    public String toString() {
        return "User [id="
            + id
            + ", username="
            + username
            + ", email="
            + email
            + ", password="
            + password
            + ", address="
            + address
            + ", contactNumber="
            + contactNumber
            + ", firstName="
            + firstName
            + ", lastName="
            + lastName
            + ", createdAt="
            + createdAt
            + ", updatedAt="
            + updatedAt
            + ", lastLogin="
            + lastLogin
            + ", schedulePayments="
            + schedulePayments
            + ", transfers="
            + transfers
            + ", rewardPointsTransactions="
            + pointsTransactions
            + ", vouchers="
            + vouchers
            + "]";
    }

}