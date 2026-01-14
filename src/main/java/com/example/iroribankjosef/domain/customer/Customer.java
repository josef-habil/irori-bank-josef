package com.example.iroribankjosef.domain.customer;

import com.example.iroribankjosef.domain.account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")

public class Customer {

    @Id
    @Column(name = "customer_id", columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Email(message = "Email must be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();


    public Customer(){}

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setCustomer(null);
    }
}
