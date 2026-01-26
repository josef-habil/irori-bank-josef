package com.example.iroribankjosef.api.controller.customer.dto;

import java.util.List;
import com.example.iroribankjosef.api.customer.dto.AccountSummaryResponse;
public record CustomerResponse(
        Long id,
        String customerNumber,
        String name,
        String email,
        List<AccountSummaryResponse> accounts
) {}
