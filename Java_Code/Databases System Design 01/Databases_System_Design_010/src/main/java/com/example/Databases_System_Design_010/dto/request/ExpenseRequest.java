package com.example.Databases_System_Design_010.dto.request;

import com.example.Databases_System_Design_010.enumTypes.ExpenseCategory;
import com.example.Databases_System_Design_010.enumTypes.SplitType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ExpenseRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotNull(message = "Category is required")
    private ExpenseCategory category;

    @NotNull(message = "Split type is required")
    private SplitType splitType;

    @NotNull(message = "Group UUID is required")
    private UUID groupUuid;

    @NotNull(message = "Paid by user UUID is required")
    private UUID paidByUserUuid;

    // Required only for EXACT and PERCENTAGE split types
    private List<ExpenseSplitRequest> splits;
}