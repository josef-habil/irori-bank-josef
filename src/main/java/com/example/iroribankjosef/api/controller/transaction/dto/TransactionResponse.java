package com.example.iroribankjosef.api.controller.transaction.dto;

import com.example.iroribankjosef.domain.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse(
        Long id,
        TransactionType type,
        BigDecimal amount,
        Long fromAccountId,
        Long toAccountId,
        Instant timestamp
) {}
