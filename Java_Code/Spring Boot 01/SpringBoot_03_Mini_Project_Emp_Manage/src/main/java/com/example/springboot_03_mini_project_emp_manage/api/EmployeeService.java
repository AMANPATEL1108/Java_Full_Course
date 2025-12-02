package com.example.springboot_03_mini_project_emp_manage.api;

import com.example.springboot_03_mini_project_emp_manage.api.dto.EmployeeCreateDto;

public interface EmployeeService {
    void createEmployee(EmployeeCreateDto user);
}
