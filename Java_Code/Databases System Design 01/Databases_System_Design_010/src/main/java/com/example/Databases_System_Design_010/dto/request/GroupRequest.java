package com.example.Databases_System_Design_010.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupRequest {

    @NotBlank(message = "Group name is required")
    private String name;

    private String description;
}