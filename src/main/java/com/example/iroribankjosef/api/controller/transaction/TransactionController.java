package com.example.iroribankjosef.api.controller.transaction;

import com.example.iroribankjosef.domain.transaction.Transaction;
import com.example.iroribankjosef.service.transaction.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Get transaction history for a specific account.
     *
     * GET /api/accounts/{accountId}/transactions
     */
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<com.example.iroribankjosef.api.controller.transaction.dto.TransactionResponse>> getTransactionsForAccount(
            @PathVariable Long accountId
    ) {
        return ResponseEntity.ok(
                transactionService.getTransactionsForAccount(accountId)
                        .stream()
                        .map(com.example.iroribankjosef.api.controller.transaction.TransactionMapper::toResponse)
                        .toList()
        );
    }

}
