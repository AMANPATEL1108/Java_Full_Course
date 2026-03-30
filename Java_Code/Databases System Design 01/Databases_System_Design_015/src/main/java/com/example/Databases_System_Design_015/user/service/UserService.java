package com.example.Databases_System_Design_015.user.service;

import com.example.Databases_System_Design_015.user.dto.UserRequest;
import com.example.Databases_System_Design_015.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUserById(Long id);

    UserResponse getUserByUsername(String username);

    List<UserResponse> getAllUsers();

    List<UserResponse> searchUsers(String keyword);

    List<UserResponse> getTopUsers();

    UserResponse updateUser(Long id, UserRequest request);

    void deactivateUser(Long id);

    void deleteUser(Long id);
}