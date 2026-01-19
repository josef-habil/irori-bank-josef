package com.example.iroribankjosef.persistence.account;

import com.example.iroribankjosef.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findByCustomerId(UUID CustomerId);
}
