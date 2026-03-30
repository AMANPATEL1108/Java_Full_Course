package com.example.Databases_System_Design_015.user.repository;

import com.example.Databases_System_Design_015.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByIsActive(Boolean isActive);

    List<User> findByIsVerified(Boolean isVerified);

    List<User> findByUsernameContainingIgnoreCaseOrDisplayNameContainingIgnoreCase(
            String username, String displayName);

    @Query("SELECT u FROM User u ORDER BY u.followersCount DESC")
    List<User> findTopUsersByFollowers();
}