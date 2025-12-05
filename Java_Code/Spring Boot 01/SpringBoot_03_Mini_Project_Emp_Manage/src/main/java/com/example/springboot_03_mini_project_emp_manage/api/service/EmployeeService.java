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

    List<Employee> searchByName(String name);

    List<Employee> filterByDepartment(String department);

    List<Employee> filterByDesignation(String designation);

    List<Employee> filterByStatus(String status);

    List<Employee> filterBySalaryRange(Double minSalary, Double maxSalary);

    Long getTotalEmployees();

    List<Object[]> getDepartmentWiseCount();

    Employee getHighestSalaryEmployee();

    Double getAverageSalary(String department);

}
