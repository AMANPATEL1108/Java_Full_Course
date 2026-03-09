package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.response.BalanceSummaryResponse;
import com.example.Databases_System_Design_010.dto.response.UserBalanceResponse;
import com.example.Databases_System_Design_010.entity.User;

import java.util.List;
import java.util.UUID;

public interface BalanceService {
    BalanceSummaryResponse getGroupBalanceSummary(UUID groupUuid, User currentUser);
    List<UserBalanceResponse> getOverallBalances(User currentUser);
}