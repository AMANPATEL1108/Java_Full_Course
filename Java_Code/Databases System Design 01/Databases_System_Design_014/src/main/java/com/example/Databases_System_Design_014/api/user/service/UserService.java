package com.example.Databases_System_Design_014.api.user.service;

import com.example.Databases_System_Design_014.api.user.dto.UserRequest;
import com.example.Databases_System_Design_014.api.user.dto.UserResponse;
import com.example.Databases_System_Design_014.api.user.entity.User;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUserById(Long id);

    UserResponse getUserByEmail(String email);

    List<UserResponse> getAllUsers();

    List<UserResponse> getActiveUsers();

    UserResponse updateUser(Long id, UserRequest request);

    void deactivateUser(Long id);

    void deleteUser(Long id);

    List<UserResponse> getUsersByRole(User.UserRole role);
}