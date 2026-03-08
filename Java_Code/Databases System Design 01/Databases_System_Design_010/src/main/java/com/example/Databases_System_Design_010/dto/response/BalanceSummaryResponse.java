package com.example.Databases_System_Design_010.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceSummaryResponse {
    private String groupName;
    private Double totalExpenses;
    private Double yourTotalShare;
    private Double yourTotalPaid;
    private Double netBalance;       // Positive = you are owed, Negative = you owe
    private List<UserBalanceResponse> balances;
}
