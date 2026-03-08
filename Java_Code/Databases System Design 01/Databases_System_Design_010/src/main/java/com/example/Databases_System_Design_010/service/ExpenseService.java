package com.example.Databases_System_Design_010.service;


import com.example.Databases_System_Design_010.dto.request.ExpenseRequest;
import com.example.Databases_System_Design_010.dto.response.ExpenseResponse;

import java.util.List;

public interface ExpenseService {
    ExpenseResponse addExpense(ExpenseRequest request);
    ExpenseResponse getExpenseById(Long expenseId);
    List<ExpenseResponse> getExpensesByGroup(Long groupId);
    List<ExpenseResponse> getExpensesByUser(Long userId);
    void deleteExpense(Long expenseId, Long requestedByUserId);
}