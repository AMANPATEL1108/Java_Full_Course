package com.example.Databases_System_Design_010.repository;

import com.example.Databases_System_Design_010.entity.Group;
import com.example.Databases_System_Design_010.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByUuid(UUID uuid);
    List<Group> findByCreatedBy(User createdBy);
}