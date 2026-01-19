package com.example.iroribankjosef.api.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

import java.util.UUID;

public class CustomerResponse {

    @JsonProperty("customer_id")
    private UUID customerId;

    @JsonProperty("name")
    private String name;

    @Email
    @JsonProperty("email")
    private String email;

    public CustomerResponse () {}

    public CustomerResponse(
            @JsonProperty("customer_id") UUID customerId,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email
    ) {

        this.customerId = customerId;
        this.name = name;
        this.email = email;

    }

    @JsonProperty("customer_id")
    public UUID getCustomerId() {
        return customerId;
    }

    @JsonProperty("customer_id")
    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }
}
