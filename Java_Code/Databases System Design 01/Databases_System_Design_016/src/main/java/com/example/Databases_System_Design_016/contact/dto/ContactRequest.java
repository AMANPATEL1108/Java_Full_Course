package com.example.Databases_System_Design_016.contact.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactRequest {

    @NotNull(message = "Owner user ID is required")
    private Long ownerId;

    @NotNull(message = "Contact user ID is required")
    private Long contactUserId;

    private String nickname;
}