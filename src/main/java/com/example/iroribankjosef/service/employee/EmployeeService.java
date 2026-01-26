package com.example.iroribankjosef.service.employee;

import com.example.iroribankjosef.domain.user.employee.Employee;
import com.example.iroribankjosef.domain.user.employee.EmployeeRole;
import com.example.iroribankjosef.persistence.employee.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(
            String name,
            String email,
            EmployeeRole role,
            String passwordHash
    ) {
        if (employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee with email already exists");
        }

        Employee employee = new Employee(
                name,
                generateEmployeeNumber(),
                email,
                role,
                passwordHash
        );

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee suspendEmployee(Long employeeId) {
        Employee employee = getEmployee(employeeId);
        employee.suspend();
        return employee;
    }

    public Employee activateEmployee(Long employeeId) {
        Employee employee = getEmployee(employeeId);
        employee.activate();
        return employee;
    }

    public Employee disableEmployee(Long employeeId) {
        Employee employee = getEmployee(employeeId);
        employee.disable();
        return employee;
    }

    private Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Employee not found: " + employeeId)
                );
    }

    private String generateEmployeeNumber() {
        return "EMP-" + UUID.randomUUID().toString();
    }
}
