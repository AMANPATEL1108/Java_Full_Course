package com.example.Databases_System_Design_010.controller;


import com.example.Databases_System_Design_010.dto.request.LoginRequest;
import com.example.Databases_System_Design_010.dto.request.RegisterRequest;
import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.AuthResponse;
import com.example.Databases_System_Design_010.service.AuthService;
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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        // TODO: implement
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        // TODO: implement
        return null;
    }
}