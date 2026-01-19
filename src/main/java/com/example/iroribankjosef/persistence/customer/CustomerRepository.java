package com.example.iroribankjosef.persistence.customer;

import com.example.iroribankjosef.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByEmail(String email);

}
