package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.response.BalanceSummaryResponse;
import com.example.Databases_System_Design_010.dto.response.UserBalanceResponse;

import java.util.List;

public interface BalanceService {
    // Get full balance summary for a user in a group
    BalanceSummaryResponse getBalanceSummary(Long userId, Long groupId);

    // Get overall balances for a user across all groups
    List<UserBalanceResponse> getOverallBalances(Long userId);
}