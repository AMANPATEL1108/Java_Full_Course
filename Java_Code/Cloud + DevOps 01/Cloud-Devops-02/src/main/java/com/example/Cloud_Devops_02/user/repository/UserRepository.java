package com.example.Cloud_Devops_02.user.repository;

import com.example.Cloud_Devops_02.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
