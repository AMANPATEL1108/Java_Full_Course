package com.example.Databases_System_Design_010.serviceImpl;


import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.repository.UserRepository;
import com.example.Databases_System_Design_010.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        // TODO: implement
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        // TODO: implement
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO: implement
        return null;
    }
}