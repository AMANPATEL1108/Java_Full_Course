package com.example.Databases_System_Design_010.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ExpenseSplitRequest {

    @NotNull(message = "User UUID is required")
    private UUID userUuid;

    private Double exactAmount;

    private Double percentage;
}