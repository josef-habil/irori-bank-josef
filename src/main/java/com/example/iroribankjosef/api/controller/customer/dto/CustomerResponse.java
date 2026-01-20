package com.example.iroribankjosef.api.controller.customer.dto;

import jakarta.validation.constraints.Email;

import java.util.List;


public class CustomerResponse {


    private Long id;
    private String customerNumber;
    private String name;

    @Email
    private String email;

    private List<AccountSummaryResponse> accounts;

    public CustomerResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountSummaryResponse> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountSummaryResponse> accounts) {
        this.accounts = accounts;
    }


}
