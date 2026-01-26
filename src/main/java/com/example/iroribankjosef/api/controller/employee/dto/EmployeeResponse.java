package com.example.iroribankjosef.api.controller.employee.dto;

import com.example.iroribankjosef.domain.user.employee.EmployeeRole;
import com.example.iroribankjosef.domain.user.employee.EmployeeStatus;

public record EmployeeResponse(
        Long id,
        String name,
        String employeeNumber,
        String email,
        EmployeeRole role,
        EmployeeStatus status
) {}
