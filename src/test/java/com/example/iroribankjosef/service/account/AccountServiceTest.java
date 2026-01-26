package com.example.iroribankjosef.service.account;

import com.example.iroribankjosef.domain.account.Account;
import com.example.iroribankjosef.domain.transaction.Transaction;
import com.example.iroribankjosef.persistence.account.AccountRepository;
import com.example.iroribankjosef.persistence.customer.CustomerRepository;
import com.example.iroribankjosef.persistence.transaction.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    void deposit_adds_money_and_creates_transaction() {
        // given
        Account account = new Account(null, "ACC-TEST");
        BigDecimal amount = BigDecimal.valueOf(100);

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        // when
        Account result = accountService.deposit(1L, amount);

        // then
        assertEquals(BigDecimal.valueOf(100), result.getBalance());

        verify(transactionRepository).save(any(Transaction.class));
    }




}
