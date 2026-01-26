package com.example.iroribankjosef.domain.user.employee;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, updatable = false)
    private String employeeNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    @Column(nullable = false)
    private String passwordHash;

    protected Employee() {
    }

    public Employee(
            String name,
            String employeeNumber,
            String email,
            EmployeeRole role,
            String passwordHash
    ) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.email = email;
        this.role = role;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void changePasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public void suspend() {
        this.status = EmployeeStatus.SUSPENDED;
    }

    public void activate() {
        this.status = EmployeeStatus.ACTIVE;
    }

    public void disable() {
        this.status = EmployeeStatus.DISABLED;
    }

    public boolean isActive() {
        return status == EmployeeStatus.ACTIVE;
    }
}
