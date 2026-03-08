package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.request.SettlementRequest;
import com.example.Databases_System_Design_010.dto.response.SettlementResponse;

import java.util.List;

public interface SettlementService {
    SettlementResponse settleUp(SettlementRequest request);
    SettlementResponse getSettlementById(Long settlementId);
    List<SettlementResponse> getSettlementsByGroup(Long groupId);
    List<SettlementResponse> getSettlementsByUser(Long userId);
}