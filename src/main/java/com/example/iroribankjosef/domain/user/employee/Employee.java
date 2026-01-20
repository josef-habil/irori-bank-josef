package com.example.iroribankjosef.domain.user.employee;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String employeeNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole role;

    @Column(nullable = false)
    private String passwordHash;

    protected Employee() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmployeeNumber() { return employeeNumber; }
    public String getEmail() { return email; }
    public EmployeeRole getRole() { return role; }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }




}
