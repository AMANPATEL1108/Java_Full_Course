package com.example.Databases_System_Design_016.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Size(max = 100, message = "Display name max 100 characters")
    private String displayName;

    @Size(max = 500, message = "About max 500 characters")
    private String about;

    private String profilePictureUrl;
}