package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.request.LoginRequest;
import com.example.Databases_System_Design_010.dto.request.RegisterRequest;
import com.example.Databases_System_Design_010.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}