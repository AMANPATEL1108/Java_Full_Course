package com.example.springboot_03_mini_project_emp_manage.api.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeCreateDto {


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

    private String status;
}
