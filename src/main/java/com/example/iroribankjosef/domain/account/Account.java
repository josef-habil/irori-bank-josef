package com.example.iroribankjosef.domain.account;

import com.example.iroribankjosef.domain.customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")

public class Account {

    @Id
    @Column(name = "account_id", columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_number", nullable = false, unique = true, length = 50)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Account (){}

    public Account(String accountNumber, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;

    }

    public UUID getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
