package com.example.Databases_System_Design_016.group.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class GroupRequest {

    @NotBlank(message = "Group name is required")
    @Size(max = 100, message = "Group name max 100 characters")
    private String name;

    @Size(max = 500, message = "Description max 500 characters")
    private String description;

    private String groupIconUrl;

    @NotNull(message = "Creator user ID is required")
    private Long createdById;

    private List<Long> memberIds;
}