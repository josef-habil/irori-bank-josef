package com.example.iroribankjosef.api.customer;

import com.example.iroribankjosef.api.customer.dto.CustomerRequest;
import com.example.iroribankjosef.api.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.user.customer.Customer;

public class CustomerMapper {

    private CustomerMapper () {}

    


    public static CustomerResponse toResponse(Customer customer){
        if(customer == null) return null;

        CustomerResponse response = new CustomerResponse();
        response.setCustomerId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setCustomerNumber(customer.getCustomerNumber());
        return response;
    }




}
