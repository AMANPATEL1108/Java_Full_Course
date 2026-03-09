package com.example.Databases_System_Design_010.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AddMemberRequest {

    @NotNull(message = "User UUID is required")
    private UUID userUuid;
}