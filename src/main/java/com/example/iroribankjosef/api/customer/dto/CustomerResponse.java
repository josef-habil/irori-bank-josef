package com.example.iroribankjosef.api.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;


public class CustomerResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("customerNumber")
    private String CustomerNumber;

    @JsonProperty("name")
    private String name;

    @Email
    @JsonProperty("email")
    private String email;

    public CustomerResponse () {}


    public Long getCustomerId() {
        return id;
    }


    public void setCustomerId(Long id) {
        this.id = id;
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

    public void setId(Long id) {
    }

    public String getCustomerNumber() {
        return CustomerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        CustomerNumber = customerNumber;
    }
}
