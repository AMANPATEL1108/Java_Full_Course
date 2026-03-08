package com.example.Databases_System_Design_010.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalanceResponse {
    private String withUserName;      // Balance is with this user
    private String withUserEmail;
    private Double amount;            // Positive = they owe you, Negative = you owe them
    private String status;            // "YOU OWE" or "THEY OWE"
}
