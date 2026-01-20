package com.example.iroribankjosef.api.controller.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class CustomerRequest {

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("email")
    @NotNull
    @Email
    private String email;

    public CustomerRequest() {}

    public CustomerRequest(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email
    ){
        this.name = name;
        this.email = email;

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
