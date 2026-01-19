package com.example.iroribankjosef.service.customer;

import com.example.iroribankjosef.api.customer.dto.CustomerRequest;
import com.example.iroribankjosef.api.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.account.Account;
import com.example.iroribankjosef.domain.account.AccountStatus;
import com.example.iroribankjosef.domain.customer.Customer;
import com.example.iroribankjosef.persistence.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import mapper.CustomerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer createCustomer(CustomerRequest customerRequest){
        repo.findByEmail(customerRequest.getEmail()).ifPresent(existing -> {
            throw new IllegalArgumentException("Email already exists: " + customerRequest.getEmail());
        });

        Customer saved = CustomerMapper.toEntity(customerRequest);
        repo.save(saved);
        return saved;

    }


    public List<Customer> getAllCustomers(){
        return repo.findAll().stream().toList();
    }

    @Transactional
    public void deleteCustomer(UUID customerId) {
        Customer customer = repo.findById(customerId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found: " + customerId)
                );
        repo.delete(customer);
    }

    public Optional<CustomerResponse> getCustomerById(UUID id){
        return repo.findById(id).map(CustomerMapper::toResponse);
    }






    @Transactional
    public Customer createCustomerWithAccount(String name, String email){

    Customer customer = new Customer(name, email);

        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(LocalDateTime.now());

        customer.addAccount(account);

        return repo.save(customer);

    }



}
