package com.example.Databases_System_Design_010.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceSummaryResponse {
    private UUID groupUuid;
    private String groupName;
    private Double totalExpenses;
    private Double yourTotalShare;
    private Double yourTotalPaid;
    private Double netBalance;           // positive = you are owed, negative = you owe
    private List<UserBalanceResponse> balances;
}