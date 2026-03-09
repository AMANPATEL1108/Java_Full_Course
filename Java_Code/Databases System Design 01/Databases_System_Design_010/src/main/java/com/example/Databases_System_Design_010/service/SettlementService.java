package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.request.SettlementRequest;
import com.example.Databases_System_Design_010.dto.response.SettlementResponse;
import com.example.Databases_System_Design_010.entity.User;

import java.util.List;
import java.util.UUID;

public interface SettlementService {
    SettlementResponse settleUp(SettlementRequest request, User currentUser);
    SettlementResponse getSettlementByUuid(UUID settlementUuid);
    List<SettlementResponse> getSettlementsByGroup(UUID groupUuid);
    List<SettlementResponse> getSettlementsByUser(UUID userUuid);
}