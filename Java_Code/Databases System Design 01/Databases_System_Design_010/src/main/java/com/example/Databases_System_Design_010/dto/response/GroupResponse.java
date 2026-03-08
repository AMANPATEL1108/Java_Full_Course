package com.example.Databases_System_Design_010.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    private String createdByName;
    private String createdByEmail;
    private LocalDateTime createdAt;
    private List<String> memberNames;
    private Integer totalMembers;
}