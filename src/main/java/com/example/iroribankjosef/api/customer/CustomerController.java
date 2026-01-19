package com.example.iroribankjosef.api.customer;


import com.example.iroribankjosef.api.customer.dto.CustomerRequest;
import com.example.iroribankjosef.api.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.customer.Customer;
import com.example.iroribankjosef.service.customer.CustomerService;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import mapper.CustomerMapper;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping
@RestController
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


    @PostMapping(value = "/customers")
    @Description("Creates a new customer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer savedCustomer = customerService.createCustomer(customerRequest);
        CustomerResponse response = CustomerMapper.toResponse(savedCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }

    @GetMapping(value = "/customers")
    @Description("Shows all customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomers().stream().map(CustomerMapper::toResponse).toList();

    }

    @GetMapping(value = "/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable UUID id){
        return customerService.getCustomerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping(value="/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }












}
