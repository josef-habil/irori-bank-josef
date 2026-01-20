package com.example.iroribankjosef.persistence.transaction;

import com.example.iroribankjosef.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromAccount_IdOrToAccount_Id(
            Long fromAccountId,
            Long toAccountId
    );
}
