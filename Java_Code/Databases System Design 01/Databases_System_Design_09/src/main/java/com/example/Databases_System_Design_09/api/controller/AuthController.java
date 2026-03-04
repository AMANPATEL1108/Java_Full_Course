package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.dto.request.LoginRequest;
import com.example.Databases_System_Design_09.api.dto.request.RegisterRequest;
import com.example.Databases_System_Design_09.api.dto.response.ApiResponse;
import com.example.Databases_System_Design_09.api.dto.response.AuthResponse;
import com.example.Databases_System_Design_09.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * POST /auth/register
     * Register a new user (default role: USER)
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully", response));
    }

    /**
     * POST /auth/login
     * Login and receive JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }
}