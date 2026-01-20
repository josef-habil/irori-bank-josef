package com.example.iroribankjosef.api.controller.customer;

import com.example.iroribankjosef.api.controller.customer.dto.AccountSummaryResponse;
import com.example.iroribankjosef.api.controller.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.user.customer.Customer;

public class CustomerMapper {

    private CustomerMapper () {}

    


    public static CustomerResponse toResponse(Customer customer){
        if(customer == null) return null;

        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setCustomerNumber(customer.getCustomerNumber());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());

        response.setAccounts(
                customer.getAccounts().stream()
                        .map(account -> {
                            AccountSummaryResponse ar = new AccountSummaryResponse();
                            ar.setId(account.getId());
                            ar.setAccountNumber(account.getAccountNumber());
                            ar.setBalance(account.getBalance());
                            ar.setStatus(account.getStatus().name());
                            return ar;
                        })
                        .toList()
        );

        return response;
    }




}
