package com.example.iroribankjosef.api.controller.customer;

import com.example.iroribankjosef.api.controller.customer.dto.AccountSummaryResponse;
import com.example.iroribankjosef.api.controller.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.user.customer.Customer;

public class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getCustomerNumber(),
                customer.getName(),
                customer.getEmail(),
                customer.getAccounts()
                        .stream()
                        .map(acc -> new AccountSummaryResponse(
                                acc.getId(),
                                acc.getAccountNumber(),
                                acc.getBalance(),
                                acc.getStatus()
                        ))
                        .toList()
        );
    }
}
