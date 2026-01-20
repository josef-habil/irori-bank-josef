package com.example.iroribankjosef.service.transaction;

import com.example.iroribankjosef.domain.transaction.Transaction;
import com.example.iroribankjosef.persistence.transaction.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsForAccount(Long accountId) {
        return transactionRepository
                .findByFromAccount_IdOrToAccount_Id(accountId, accountId);
    }
}
