package com.example.cloud_devops_03.api.user.service;


import com.example.cloud_devops_03.api.user.dto.Request.UserRequest;
import com.example.cloud_devops_03.api.user.dto.Response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponse createUser(UserRequest request);

    String deleteUser(Long id);

    List<UserResponse> getAllUser();

    Optional<UserResponse> getUserById(Long id);
}
