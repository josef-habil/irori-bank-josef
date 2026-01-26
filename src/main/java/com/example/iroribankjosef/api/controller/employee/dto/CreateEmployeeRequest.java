package com.example.iroribankjosef.api.controller.employee.dto;

import com.example.iroribankjosef.domain.user.employee.EmployeeRole;

public record CreateEmployeeRequest(
        String name,
        String email,
        EmployeeRole role,
        String password
) {}
