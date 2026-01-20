package com.example.iroribankjosef.api.controller.transaction;

import com.example.iroribankjosef.api.controller.transaction.dto.TransactionResponse;
import com.example.iroribankjosef.domain.transaction.Transaction;
public class TransactionMapper {

    public static TransactionResponse toResponse(Transaction tx) {
        TransactionResponse dto = new TransactionResponse();
        dto.setId(tx.getId());
        dto.setType(tx.getType().name());
        dto.setAmount(tx.getAmount());
        dto.setTimestamp(tx.getTimestamp());

        if (tx.getFromAccount() != null) {
            dto.setFromAccountId(tx.getFromAccount().getId());
        }

        if (tx.getToAccount() != null) {
            dto.setToAccountId(tx.getToAccount().getId());
        }

        return dto;
    }

}
