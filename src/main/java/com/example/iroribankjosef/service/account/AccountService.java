package com.example.iroribankjosef.service.account;

import com.example.iroribankjosef.domain.account.Account;
import com.example.iroribankjosef.domain.transaction.Transaction;
import com.example.iroribankjosef.persistence.account.AccountRepository;
import com.example.iroribankjosef.persistence.customer.CustomerRepository;
import com.example.iroribankjosef.persistence.transaction.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            TransactionRepository transactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccountForCustomer(Long customerId) {

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Account account = new Account(customer, generateAccountNumber());
        customer.addAccount(account);

        return accountRepository.save(account);
    }

    public Account deposit(Long accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.deposit(amount);

        transactionRepository.save(
                Transaction.deposit(account, amount)
        );

        return account;
    }

    public Account withdraw(Long accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.withdraw(amount);

        transactionRepository.save(
                Transaction.withdrawal(account, amount)
        );

        return account;
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException("Customer not found");
        }
        return accountRepository.findByCustomerId(customerId);
    }

    private String generateAccountNumber() {
        return "ACC-" + System.currentTimeMillis();
    }
}
