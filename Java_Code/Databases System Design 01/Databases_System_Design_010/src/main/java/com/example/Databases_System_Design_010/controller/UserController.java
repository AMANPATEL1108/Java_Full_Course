package com.example.Databases_System_Design_010.controller;

import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.UserResponse;
import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET /api/users/me  — get currently logged-in user
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        UserResponse response = userService.getUserByUuid(currentUser.getUuid());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/users/{uuid}
    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUuid(@PathVariable UUID uuid) {
        UserResponse response = userService.getUserByUuid(uuid);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/users  — ADMIN only
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success(users));
    }
}