package com.example.Databases_System_Design_016.user.service;

import com.example.Databases_System_Design_016.user.dto.UserRequest;
import com.example.Databases_System_Design_016.user.dto.UserResponse;
import com.example.Databases_System_Design_016.user.entity.User;

import java.util.List;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    UserResponse getUserById(Long id);

    UserResponse getUserByPhoneNumber(String phoneNumber);

    List<UserResponse> getAllUsers();

    List<UserResponse> searchUsersByName(String name);

    List<UserResponse> getUsersByOnlineStatus(User.OnlineStatus status);

    UserResponse updateUser(Long id, UserRequest request);

    UserResponse updateOnlineStatus(Long id, User.OnlineStatus status);

    void deactivateUser(Long id);

    void deleteUser(Long id);
}