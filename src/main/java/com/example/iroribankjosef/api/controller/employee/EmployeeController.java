package com.example.iroribankjosef.api.controller.employee;

import com.example.iroribankjosef.api.controller.employee.dto.CreateEmployeeRequest;
import com.example.iroribankjosef.api.controller.employee.dto.EmployeeMapper;
import com.example.iroribankjosef.api.controller.employee.dto.EmployeeResponse;
import com.example.iroribankjosef.domain.user.employee.Employee;
import com.example.iroribankjosef.service.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest request) {
        Employee employee = employeeService.createEmployee(
                request.name(),
                request.email(),
                request.role(),
                request.password()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }


    @PutMapping("/{id}/suspend")
    public Employee suspendEmployee(@PathVariable Long id) {
        return employeeService.suspendEmployee(id);
    }

    @PutMapping("/{id}/activate")
    public Employee activateEmployee(@PathVariable Long id) {
        return employeeService.activateEmployee(id);
    }

    @PutMapping("/{id}/disable")
    public Employee disableEmployee(@PathVariable Long id) {
        return employeeService.disableEmployee(id);
    }
}
