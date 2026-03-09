package com.example.Databases_System_Design_010.repository;

import com.example.Databases_System_Design_010.entity.Group;
import com.example.Databases_System_Design_010.entity.GroupMember;
import com.example.Databases_System_Design_010.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findByGroup(Group group);
    List<GroupMember> findByUser(User user);
    boolean existsByGroupAndUser(Group group, User user);
    Optional<GroupMember> findByGroupAndUser(Group group, User user);
}