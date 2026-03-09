package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.request.ExpenseRequest;
import com.example.Databases_System_Design_010.dto.response.ExpenseResponse;
import com.example.Databases_System_Design_010.entity.User;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    ExpenseResponse addExpense(ExpenseRequest request, User currentUser);
    ExpenseResponse getExpenseByUuid(UUID expenseUuid);
    List<ExpenseResponse> getExpensesByGroup(UUID groupUuid);
    List<ExpenseResponse> getExpensesByUser(UUID userUuid);
    void deleteExpense(UUID expenseUuid, User currentUser);
}