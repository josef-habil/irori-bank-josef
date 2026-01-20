package com.example.iroribankjosef.api.controller.account;

import com.example.iroribankjosef.api.controller.account.dto.AccountResponse;
import com.example.iroribankjosef.api.controller.account.dto.DepositRequest;
import com.example.iroribankjosef.api.controller.account.dto.WithdrawRequest;
import com.example.iroribankjosef.domain.account.Account;
import com.example.iroribankjosef.service.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId) {

        Account account = accountService.createAccountForCustomer(customerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "id", account.getId(),
                        "accountNumber", account.getAccountNumber(),
                        "balance", account.getBalance(),
                        "status", account.getStatus()
                )
        );
    }

    @PostMapping("/accounts/{accountId}/deposit")
    public ResponseEntity<Void> deposit(
            @PathVariable Long accountId,
            @Valid @RequestBody DepositRequest request
    ) {
        accountService.deposit(accountId, request.getAmount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accounts/{accountId}/withdraw")
    public ResponseEntity<Void> withdraw(
            @PathVariable Long accountId,
            @Valid @RequestBody WithdrawRequest withdrawRequest
    ){
        accountService.withdraw(accountId, withdrawRequest.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<List<AccountResponse>> getAccountsByCustomer(
            @PathVariable Long customerId
    ) {
        List<AccountResponse> accounts = accountService
                .getAccountsByCustomerId(customerId)
                .stream()
                .map(AccountMapper::toResponse)
                .toList();

        return ResponseEntity.ok(accounts);
    }







}
