package com.example.Cloud_Devops_02.user.service;

import com.example.Cloud_Devops_02.user.dto.Request.UserRequest;
import com.example.Cloud_Devops_02.user.dto.Response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponse createUser(UserRequest request);

    String deleteUser(Long id);

    List<UserResponse> getAllUser();

    Optional<UserResponse> getUserById(Long id);
}
