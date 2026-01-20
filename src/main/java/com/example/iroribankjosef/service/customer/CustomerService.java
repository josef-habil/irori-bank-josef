package com.example.iroribankjosef.service.customer;

import com.example.iroribankjosef.api.controller.customer.CustomerMapper;
import com.example.iroribankjosef.api.controller.customer.dto.CustomerRequest;

import com.example.iroribankjosef.api.controller.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.user.customer.Customer;
import com.example.iroribankjosef.persistence.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CustomerRequest customerRequest){
        customerRepository.findByEmail(customerRequest.getEmail()).ifPresent(existing -> {
            throw new IllegalArgumentException("Email already exists: " + customerRequest.getEmail());
        });

        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setCustomerNumber(generateCustomerNumber());
        customer.setPasswordHash("TEMP");

        return customerRepository.save(customer);

    }


    public List<Customer> getAllCustomers(){
        return customerRepository.findAll().stream().toList();
    }

    @Transactional
    public void deleteCustomer(Long  customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found: " + customerId)
                );
        customerRepository.delete(customer);
    }

    public Optional<CustomerResponse> getCustomerById(Long id){
        return customerRepository.findById(id).map(CustomerMapper::toResponse);
    }

    private String generateCustomerNumber() {
        return "CUST-" + System.currentTimeMillis();
    }




    /*@Transactional
    public Customer createCustomerWithAccount(String name, String email){

    Customer customer = new Customer(name, email);

        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(LocalDateTime.now());

        customer.addAccount(account);

        return customerRepository.save(customer);

    }*/



}
