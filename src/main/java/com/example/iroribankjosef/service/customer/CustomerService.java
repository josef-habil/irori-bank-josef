package com.example.iroribankjosef.service.customer;

import com.example.iroribankjosef.api.customer.CustomerMapper;
import com.example.iroribankjosef.api.customer.dto.CustomerRequest;

import com.example.iroribankjosef.api.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.user.customer.Customer;
import com.example.iroribankjosef.persistence.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setCustomerNumber(generateCustomerNumber());
        customer.setPasswordHash("TEMP");

        return repo.save(customer);

    }


    public List<Customer> getAllCustomers(){
        return repo.findAll().stream().toList();
    }

    @Transactional
    public void deleteCustomer(Long  customerId) {
        Customer customer = repo.findById(customerId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found: " + customerId)
                );
        repo.delete(customer);
    }

    public Optional<CustomerResponse> getCustomerById(Long id){
        return repo.findById(id).map(CustomerMapper::toResponse);
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

        return repo.save(customer);

    }*/



}
