package com.example.Databases_System_Design_010.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddMemberRequest {

    @NotNull(message = "User ID is required")
    private Long userId;
}