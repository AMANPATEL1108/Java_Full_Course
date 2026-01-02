package com.example.springboot_03_mini_project_emp_manage.api.controller;


import com.example.springboot_03_mini_project_emp_manage.api.dto.EmployeeCreateDto;
import com.example.springboot_03_mini_project_emp_manage.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeCreateDto user) {
        employeeService.createEmployee(user);
        return ResponseEntity.ok("User Created");
    }
}