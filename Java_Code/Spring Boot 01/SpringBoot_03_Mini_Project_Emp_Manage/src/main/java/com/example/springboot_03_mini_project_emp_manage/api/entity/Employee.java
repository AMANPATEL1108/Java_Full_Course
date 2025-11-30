package com.example.springboot_03_mini_project_emp_manage.api.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String designation;
    private Double salary;
    private String department;

    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;

    private String status;  // Active or Inactive

    // Getters and setters
}
