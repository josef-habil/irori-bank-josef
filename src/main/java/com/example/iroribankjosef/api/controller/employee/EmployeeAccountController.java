package com.example.iroribankjosef.api.controller.employee;

import com.example.iroribankjosef.domain.account.Account;
import com.example.iroribankjosef.service.account.AccountService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees/accounts")
public class EmployeeAccountController {

    private final AccountService accountService;

    public EmployeeAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping("/{accountId}/freeze")
    public Account freeze(@PathVariable Long accountId) {
        return accountService.freezeAccount(accountId);
    }

    @PutMapping("/{accountId}/activate")
    public Account activate(@PathVariable Long accountId) {
        return accountService.activateAccount(accountId);
    }

    @PutMapping("/{accountId}/close")
    public Account close(@PathVariable Long accountId) {
        return accountService.closeAccount(accountId);
    }
}
