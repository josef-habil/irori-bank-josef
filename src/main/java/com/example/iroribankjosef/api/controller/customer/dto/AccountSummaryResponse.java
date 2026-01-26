package com.example.iroribankjosef.api.controller.customer.dto;

import com.example.iroribankjosef.domain.account.AccountStatus;

import java.math.BigDecimal;

public record AccountSummaryResponse(
        Long id,
        String accountNumber,
        BigDecimal balance,
        AccountStatus status
) {}
