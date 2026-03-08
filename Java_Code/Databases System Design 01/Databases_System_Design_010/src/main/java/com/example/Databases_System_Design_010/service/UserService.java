package com.example.Databases_System_Design_010.service;


import com.example.Databases_System_Design_010.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
}