package com.example.iroribankjosef.api.controller.employee.dto;

import com.example.iroribankjosef.api.controller.employee.dto.EmployeeResponse;
import com.example.iroribankjosef.domain.user.employee.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {}

    public static EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmployeeNumber(),
                employee.getEmail(),
                employee.getRole(),
                employee.getStatus()
        );
    }
}
