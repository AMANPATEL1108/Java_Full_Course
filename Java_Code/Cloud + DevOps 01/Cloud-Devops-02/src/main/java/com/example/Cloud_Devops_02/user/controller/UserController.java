package com.example.Cloud_Devops_02.user.controller;

import com.example.Cloud_Devops_02.user.dto.Request.UserRequest;
import com.example.Cloud_Devops_02.user.dto.Response.UserResponse;
import com.example.Cloud_Devops_02.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public Optional<UserResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}