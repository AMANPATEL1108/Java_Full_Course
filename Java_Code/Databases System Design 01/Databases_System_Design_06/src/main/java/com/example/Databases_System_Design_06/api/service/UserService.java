package com.example.Databases_System_Design_06.api.service;

import com.example.Databases_System_Design_06.api.entity.User;
import com.example.Databases_System_Design_06.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPublisher userPublisher;

    // READ (Cache)
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    // UPDATE (Cache + Publish)
    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        User updatedUser = userRepository.save(user);

        // Publish event
        userPublisher.publish("User Updated: " + user.getId());

        return updatedUser;
    }

    // DELETE (Cache + Publish)
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

        // Publish event
        userPublisher.publish("User Deleted: " + id);
    }
}
