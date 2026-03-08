package com.example.Databases_System_Design_010.repository;

import com.example.Databases_System_Design_010.entity.Expense;
import com.example.Databases_System_Design_010.entity.ExpenseSplit;
import com.example.Databases_System_Design_010.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {
    List<ExpenseSplit> findByExpense(Expense expense);
    List<ExpenseSplit> findByUser(User user);
    List<ExpenseSplit> findByUserAndSettled(User user, Boolean settled);

    // All unsettled splits for a user in a specific group
    @Query("SELECT es FROM ExpenseSplit es WHERE es.user = :user AND es.settled = false AND es.expense.group.id = :groupId")
    List<ExpenseSplit> findUnsettledByUserAndGroup(User user, Long groupId);
}