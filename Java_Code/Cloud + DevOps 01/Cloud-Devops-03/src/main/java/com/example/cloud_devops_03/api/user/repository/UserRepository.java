package com.example.cloud_devops_03.api.user.repository;

import com.example.cloud_devops_03.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
