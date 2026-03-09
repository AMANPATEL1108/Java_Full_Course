package com.example.Databases_System_Design_010.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBalanceResponse {
    private UUID withUserUuid;
    private String withUserName;
    private String withUserEmail;
    private Double amount;      // positive = they owe you, negative = you owe them
    private String status;      // "YOU OWE" or "THEY OWE YOU"
}