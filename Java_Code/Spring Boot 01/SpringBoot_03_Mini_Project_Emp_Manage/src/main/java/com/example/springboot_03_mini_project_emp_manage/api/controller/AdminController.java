package com.example.springboot_03_mini_project_emp_manage.api.controller;

import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import com.example.springboot_03_mini_project_emp_manage.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateByEmployeeId(@PathVariable Long id, @RequestBody Employee employee) {
        HashMap<String, Object> response = new HashMap<>();
        Employee e = employeeService.updatedByEmployee(id, employee);
        response.put("employee", e);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByEmployeeId(@PathVariable Long id) {
        HashMap<String, Object> response = new HashMap<>();
        Employee e = employeeService.employeeDeleteByID(id);
        response.put("employee", e);
        return ResponseEntity.ok(response);
    }

}
