package com.example.Databases_System_Design_016.user.repository;

import com.example.Databases_System_Design_016.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    List<User> findByIsActive(Boolean isActive);

    List<User> findByOnlineStatus(User.OnlineStatus onlineStatus);

    List<User> findByDisplayNameContainingIgnoreCase(String name);
}