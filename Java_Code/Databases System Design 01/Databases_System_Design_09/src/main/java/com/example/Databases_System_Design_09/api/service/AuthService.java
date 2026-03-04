package com.example.Databases_System_Design_09.api.service;

import com.example.Databases_System_Design_09.api.dto.request.LoginRequest;
import com.example.Databases_System_Design_09.api.dto.request.RegisterRequest;
import com.example.Databases_System_Design_09.api.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}