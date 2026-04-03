package com.example.Databases_System_Design_016.group.repository;

import com.example.Databases_System_Design_016.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByCreatedByIdAndIsActive(Long createdById, Boolean isActive);

    List<Group> findByNameContainingIgnoreCaseAndIsActive(String name, Boolean isActive);

    @Query("SELECT g FROM Group g JOIN g.members gm WHERE gm.user.id = :userId AND g.isActive = true")
    List<Group> findGroupsByMemberId(Long userId);
}