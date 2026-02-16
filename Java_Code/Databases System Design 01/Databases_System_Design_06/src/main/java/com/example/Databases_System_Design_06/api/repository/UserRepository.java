package com.example.Databases_System_Design_06.api.repository;

import com.example.Databases_System_Design_06.api.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Map<Long, User> database = new HashMap<>();

    public UserRepository() {
        database.put(1L, new User(1L, "John"));
        database.put(2L, new User(2L, "Alice"));
    }

    public User findById(Long id) {
        System.out.println("Fetching from DB...");
        return database.get(id);
    }

    public User save(User user) {
        database.put(user.getId(), user);
        return user;
    }

    public void deleteById(Long id) {
        database.remove(id);
    }
}
