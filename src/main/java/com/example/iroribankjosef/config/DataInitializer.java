package com.example.iroribankjosef.config;

import com.example.iroribankjosef.domain.user.employee.EmployeeRole;
import com.example.iroribankjosef.persistence.employee.EmployeeRepository;
import com.example.iroribankjosef.service.employee.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {


    @Bean
    CommandLineRunner initAdmin(EmployeeService employeeService,
                                EmployeeRepository employeeRepository) {
        return args -> {
            if (employeeRepository.count() == 0) {
                employeeService.createEmployee(
                        "System Admin",
                        "admin@irori-bank.com",
                        EmployeeRole.ADMIN,
                        "TEMP_HASH"
                );
            }
        };
    }


}
