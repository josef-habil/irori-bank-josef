package com.example.iroribankjosef.api.controller.account;

import com.example.iroribankjosef.api.controller.account.dto.AccountResponse;
import com.example.iroribankjosef.domain.account.Account;

public class AccountMapper {

    private AccountMapper() {}

    public static AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getStatus(),
                account.getCreatedAt()
        );
    }
}
