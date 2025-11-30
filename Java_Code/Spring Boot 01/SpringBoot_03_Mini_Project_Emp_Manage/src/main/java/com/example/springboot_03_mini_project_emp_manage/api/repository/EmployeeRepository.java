package com.example.springboot_03_mini_project_emp_manage.api.repository;

import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
