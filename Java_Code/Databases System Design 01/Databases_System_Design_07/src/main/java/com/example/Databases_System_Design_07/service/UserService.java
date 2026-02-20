package com.example.Databases_System_Design_07.service;

import com.example.Databases_System_Design_07.entity.User;
import com.example.Databases_System_Design_07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPublisher userPublisher;

    // CREATE
    public User createUser(User user) {
        User savedUser = userRepository.save(user);

        // Publish event
        userPublisher.publish("User Created: " + savedUser.getId());

        return savedUser;
    }

    // READ (Cache)
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // UPDATE (Cache + Publish)
    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        User updatedUser = userRepository.save(user);

        userPublisher.publish("User Updated: " + updatedUser.getId());

        return updatedUser;
    }

    // DELETE (Cache + Publish)
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

        userPublisher.publish("User Deleted: " + id);
    }

//    @Cacheable(value = "usersList", key = "'all'")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
