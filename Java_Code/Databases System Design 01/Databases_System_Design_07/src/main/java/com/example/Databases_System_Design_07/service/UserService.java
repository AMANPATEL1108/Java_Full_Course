package com.example.Databases_System_Design_07.service;

import com.example.Databases_System_Design_07.entity.User;
import com.example.Databases_System_Design_07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        System.out.println("Calling From Database.........");
        return user.get();
    }

    @CachePut(value = "users", key = "#id")
    public User updateUser(User user, Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setWork(user.getWork());
        existingUser.setReward(user.getReward());
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }

    public User createUser(User user) {
        User created = userRepository.save(user);
        return created;
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
