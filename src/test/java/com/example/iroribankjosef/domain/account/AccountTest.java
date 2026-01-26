package com.example.iroribankjosef.domain.account;

import com.example.iroribankjosef.domain.user.customer.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account createAccountWithBalance(BigDecimal balance) {
        Customer customer = new Customer();
        Account account = new Account(customer, "ACC-TEST");
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            account.deposit(balance);
        }
        return account;
    }

    @Test
    void deposit_increases_balance_when_account_active() {
        Account account = createAccountWithBalance(BigDecimal.ZERO);

        account.deposit(BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(100), account.getBalance());
    }

    @Test
    void withdraw_decreases_balance_when_sufficient_funds() {
        Account account = createAccountWithBalance(BigDecimal.valueOf(200));

        account.withdraw(BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(150), account.getBalance());
    }

    @Test
    void withdraw_throws_when_insufficient_funds() {
        Account account = createAccountWithBalance(BigDecimal.valueOf(50));

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> account.withdraw(BigDecimal.valueOf(100))
        );

        assertEquals("Insufficient funds", ex.getMessage());
    }

    @Test
    void deposit_fails_when_account_frozen() {
        Account account = createAccountWithBalance(BigDecimal.ZERO);
        account.freeze();

        assertThrows(
                IllegalStateException.class,
                () -> account.deposit(BigDecimal.TEN)
        );
    }

    @Test
    void freeze_changes_status_to_frozen() {
        Account account = createAccountWithBalance(BigDecimal.ZERO);

        account.freeze();

        assertEquals(AccountStatus.FROZEN, account.getStatus());
    }

    @Test
    void activate_only_works_when_frozen() {
        Account account = createAccountWithBalance(BigDecimal.ZERO);
        account.freeze();

        account.activate();

        assertEquals(AccountStatus.ACTIVE, account.getStatus());
    }

    @Test
    void close_fails_if_balance_not_zero() {
        Account account = createAccountWithBalance(BigDecimal.TEN);

        assertThrows(
                IllegalStateException.class,
                account::close
        );
    }

    @Test
    void close_sets_status_to_closed_when_balance_zero() {
        Account account = createAccountWithBalance(BigDecimal.ZERO);

        account.close();

        assertEquals(AccountStatus.CLOSED, account.getStatus());
    }
}
