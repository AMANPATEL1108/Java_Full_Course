package com.example.springboot_03_mini_project_emp_manage.api.service;

import com.example.springboot_03_mini_project_emp_manage.api.dto.EmployeeCreateDto;
import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;

import java.util.List;

public interface EmployeeService {

    void createEmployee(EmployeeCreateDto user);

    List<Employee> getEmployee(EmployeeCreateDto user);

    Employee getEmployeeById(Long id);

    Employee updatedByEmployee(Long id, Employee employee);

    Employee employeeDeleteByID(Long id);
}
