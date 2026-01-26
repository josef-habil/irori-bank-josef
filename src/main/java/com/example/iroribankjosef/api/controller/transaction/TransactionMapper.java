package com.example.iroribankjosef.api.controller.transaction;

import com.example.iroribankjosef.api.controller.transaction.dto.TransactionResponse;
import com.example.iroribankjosef.domain.transaction.Transaction;

public class TransactionMapper {

    private TransactionMapper() {}

    public static TransactionResponse toResponse(Transaction tx) {
        return new TransactionResponse(
                tx.getId(),
                tx.getType(),
                tx.getAmount(),
                tx.getFromAccount() != null ? tx.getFromAccount().getId() : null,
                tx.getToAccount() != null ? tx.getToAccount().getId() : null,
                tx.getTimestamp()
        );
    }
}
