package com.example.iroribankjosef.api.controller.customer;


import com.example.iroribankjosef.api.controller.customer.dto.CustomerRequest;
import com.example.iroribankjosef.api.controller.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.user.customer.Customer;
import com.example.iroribankjosef.service.customer.CustomerService;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck(){
        return "pong";
    }


    @PostMapping
    @Description("Creates a new customer")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer savedCustomer = customerService.createCustomer(customerRequest);
        CustomerResponse response = CustomerMapper.toResponse(savedCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }

    @GetMapping
    @Description("Shows all customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomers().stream().filter(Objects::nonNull).map(CustomerMapper::toResponse).toList();

    }

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id){
        return ResponseEntity.of(customerService.getCustomerById(id));

    }



    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }













}
