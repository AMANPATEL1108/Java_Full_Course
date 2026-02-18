package com.example.Databases_System_Design_07.repository;

import com.example.Databases_System_Design_07.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
