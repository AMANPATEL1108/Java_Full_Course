package com.example.springboot_03_mini_project_emp_manage.api.serviceImpl;

import com.example.springboot_03_mini_project_emp_manage.api.EmployeeService;
import com.example.springboot_03_mini_project_emp_manage.api.dto.EmployeeCreateDto;
import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import com.example.springboot_03_mini_project_emp_manage.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void createEmployee(EmployeeCreateDto user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required.");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required.");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required.");
        }
        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address is required.");
        }
        if (user.getDesignation() == null || user.getDesignation().isEmpty()) {
            throw new IllegalArgumentException("Designation is required.");
        }
        if (user.getSalary() == null) {
            throw new IllegalArgumentException("Salary is required.");
        }
        if (user.getDepartment() == null || user.getDepartment().isEmpty()) {
            throw new IllegalArgumentException("Department is required.");
        }
        if (user.getDateOfJoining() == null) {
            throw new IllegalArgumentException("Date of joining is required.");
        }
        if (user.getStatus() == null || user.getStatus().isEmpty()) {
            throw new IllegalArgumentException("Status is required.");
        }

        Employee employee = new Employee();
        employee.setFirstName(user.getFirstName());
        employee.setLastName(user.getLastName());
        employee.setEmail(user.getEmail());
        employee.setPhoneNumber(user.getPhoneNumber());
        employee.setAddress(user.getAddress());
        employee.setDesignation(user.getDesignation());
        employee.setSalary(user.getSalary());
        employee.setDepartment(user.getDepartment());
        employee.setDateOfJoining(user.getDateOfJoining());
        employee.setStatus(user.getStatus());

        employeeRepository.save(employee);

    }

}
