package com.example.Databases_System_Design_016.status.dto;

import com.example.Databases_System_Design_016.status.entity.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Status type is required")
    private Status.StatusType statusType;

    private String caption;

    private String mediaUrl;

    private String backgroundColor;

    private String fontStyle;
}