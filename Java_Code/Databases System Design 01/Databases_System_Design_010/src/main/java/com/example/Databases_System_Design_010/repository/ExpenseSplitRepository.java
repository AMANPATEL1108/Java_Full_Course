package com.example.Databases_System_Design_010.repository;

import com.example.Databases_System_Design_010.entity.Expense;
import com.example.Databases_System_Design_010.entity.ExpenseSplit;
import com.example.Databases_System_Design_010.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {
    List<ExpenseSplit> findByExpense(Expense expense);
    List<ExpenseSplit> findByUser(User user);
    List<ExpenseSplit> findByUserAndSettled(User user, Boolean settled);

    @Query("SELECT es FROM ExpenseSplit es WHERE es.user = :user AND es.settled = false AND es.expense.group.uuid = :groupUuid")
    List<ExpenseSplit> findUnsettledByUserAndGroupUuid(@Param("user") User user, @Param("groupUuid") UUID groupUuid);

    @Query("SELECT es FROM ExpenseSplit es WHERE es.expense.group.uuid = :groupUuid")
    List<ExpenseSplit> findByGroupUuid(@Param("groupUuid") UUID groupUuid);
}