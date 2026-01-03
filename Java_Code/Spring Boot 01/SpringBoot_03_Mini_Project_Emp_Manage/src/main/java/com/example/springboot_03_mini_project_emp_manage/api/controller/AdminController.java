package com.example.springboot_03_mini_project_emp_manage.api.controller;

import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import com.example.springboot_03_mini_project_emp_manage.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateByEmployeeId(@PathVariable Long id, @RequestBody Employee employee) {
        HashMap<String, Object> response = new HashMap<>();
        Employee e = employeeService.updatedByEmployee(id, employee);
        if (e == null) {
            response.put("message", "Employee not found");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("employee", e);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByEmployeeId(@PathVariable Long id) {
        HashMap<String, Object> response = new HashMap<>();
        Employee e = employeeService.employeeDeleteByID(id);
        if (e == null) {
            response.put("message", "Employee not found");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("employee", e);
        response.put("message", "Employee deleted successfully");
        return ResponseEntity.ok(response);
    }
}