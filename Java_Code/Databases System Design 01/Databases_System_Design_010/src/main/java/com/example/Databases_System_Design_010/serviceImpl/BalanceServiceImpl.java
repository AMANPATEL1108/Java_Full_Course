package com.example.Databases_System_Design_010.serviceImpl;


import com.example.Databases_System_Design_010.dto.response.BalanceSummaryResponse;
import com.example.Databases_System_Design_010.dto.response.UserBalanceResponse;
import com.example.Databases_System_Design_010.repository.*;
import com.example.Databases_System_Design_010.service.BalanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final ExpenseSplitRepository expenseSplitRepository;
    private final ExpenseRepository expenseRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;
    private final SettlementRepository settlementRepository;

    public BalanceServiceImpl(ExpenseSplitRepository expenseSplitRepository,
                              ExpenseRepository expenseRepository,
                              GroupRepository groupRepository,
                              GroupMemberRepository groupMemberRepository,
                              UserRepository userRepository,
                              SettlementRepository settlementRepository) {
        this.expenseSplitRepository = expenseSplitRepository;
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.userRepository = userRepository;
        this.settlementRepository = settlementRepository;
    }

    @Override
    public BalanceSummaryResponse getBalanceSummary(Long userId, Long groupId) {
        // TODO: implement
        return null;
    }

    @Override
    public List<UserBalanceResponse> getOverallBalances(Long userId) {
        // TODO: implement
        return null;
    }
}