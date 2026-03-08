package com.example.Databases_System_Design_010.controller;


import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        // TODO: implement
        return null;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        // TODO: implement
        return null;
    }
}