package com.example.springboot_03_mini_project_emp_manage.api.service;

import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(Employee user);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}