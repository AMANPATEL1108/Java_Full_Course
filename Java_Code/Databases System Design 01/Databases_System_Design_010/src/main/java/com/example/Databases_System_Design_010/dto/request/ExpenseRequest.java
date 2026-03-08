package com.example.Databases_System_Design_010.dto.request;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ExpenseRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank(message = "Category is required")
    private String category;      // FOOD, TRAVEL, RENT, SHOPPING, ENTERTAINMENT, OTHER

    @NotBlank(message = "Split type is required")
    private String splitType;     // EQUAL, EXACT, PERCENTAGE

    @NotNull(message = "Group ID is required")
    private Long groupId;

    @NotNull(message = "Paid by user ID is required")
    private Long paidByUserId;

    // Required for EXACT and PERCENTAGE split types
    // For EQUAL split this can be null — auto split among all group members
    private List<ExpenseSplitRequest> splits;
}