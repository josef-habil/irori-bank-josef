package com.example.iroribankjosef.api.controller.account.dto;

import com.example.iroribankjosef.domain.account.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(
        Long id,
        String accountNumber,
        BigDecimal balance,
        AccountStatus status,
        LocalDateTime createdAt
) {}
