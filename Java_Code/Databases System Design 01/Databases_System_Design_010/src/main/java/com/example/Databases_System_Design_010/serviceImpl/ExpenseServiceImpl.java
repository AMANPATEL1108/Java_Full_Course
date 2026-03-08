package com.example.Databases_System_Design_010.serviceImpl;


import com.example.Databases_System_Design_010.dto.request.ExpenseRequest;
import com.example.Databases_System_Design_010.dto.response.ExpenseResponse;
import com.example.Databases_System_Design_010.repository.*;
import com.example.Databases_System_Design_010.service.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              ExpenseSplitRepository expenseSplitRepository,
                              GroupRepository groupRepository,
                              GroupMemberRepository groupMemberRepository,
                              UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseSplitRepository = expenseSplitRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ExpenseResponse addExpense(ExpenseRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public ExpenseResponse getExpenseById(Long expenseId) {
        // TODO: implement
        return null;
    }

    @Override
    public List<ExpenseResponse> getExpensesByGroup(Long groupId) {
        // TODO: implement
        return null;
    }

    @Override
    public List<ExpenseResponse> getExpensesByUser(Long userId) {
        // TODO: implement
        return null;
    }

    @Override
    @Transactional
    public void deleteExpense(Long expenseId, Long requestedByUserId) {
        // TODO: implement
    }
}