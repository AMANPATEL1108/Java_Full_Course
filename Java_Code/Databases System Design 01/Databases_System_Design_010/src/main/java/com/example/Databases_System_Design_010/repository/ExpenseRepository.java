package com.example.Databases_System_Design_010.repository;

import com.example.Databases_System_Design_010.entity.Expense;
import com.example.Databases_System_Design_010.entity.Group;
import com.example.Databases_System_Design_010.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByGroup(Group group);
    List<Expense> findByPaidBy(User user);
    List<Expense> findByGroupAndPaidBy(Group group, User user);
}
