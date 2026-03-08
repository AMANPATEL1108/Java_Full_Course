package com.example.Databases_System_Design_010.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpenseSplitRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    // For EXACT split — exact amount this user owes
    private Double exactAmount;

    // For PERCENTAGE split — percentage share
    private Double percentage;
}