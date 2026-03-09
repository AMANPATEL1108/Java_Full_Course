package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse getUserByUuid(UUID uuid);
    UserResponse getUserByEmail(String email);
    List<UserResponse> getAllUsers();
}