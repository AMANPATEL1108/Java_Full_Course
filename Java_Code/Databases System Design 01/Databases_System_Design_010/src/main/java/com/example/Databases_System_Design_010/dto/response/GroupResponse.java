package com.example.Databases_System_Design_010.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupResponse {
    private UUID uuid;
    private String name;
    private String description;
    private String createdByName;
    private String createdByEmail;
    private UUID createdByUuid;
    private LocalDateTime createdAt;
    private List<MemberDetail> members;
    private int totalMembers;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberDetail {
        private UUID userUuid;
        private String name;
        private String email;
    }
}