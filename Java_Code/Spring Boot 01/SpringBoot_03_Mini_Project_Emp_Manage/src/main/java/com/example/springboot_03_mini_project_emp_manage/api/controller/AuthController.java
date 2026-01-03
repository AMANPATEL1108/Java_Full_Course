package com.example.springboot_03_mini_project_emp_manage.api.controller;

import com.example.springboot_03_mini_project_emp_manage.api.dto.AuthRequest;
import com.example.springboot_03_mini_project_emp_manage.api.dto.EmployeeCreateDto;
import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import com.example.springboot_03_mini_project_emp_manage.api.service.EmployeeService;
import com.example.springboot_03_mini_project_emp_manage.api.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeCreateDto user) {
        try {
            employeeService.createEmployee(user);
            return ResponseEntity.ok("User Created Successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            Employee user = (Employee) auth.getPrincipal();
            return ResponseEntity.ok(Map.of("token", jwtService.generateToken(user)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }
}