package com.example.Databases_System_Design_016.user.dto;

import com.example.Databases_System_Design_016.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String phoneNumber;
    private String displayName;
    private String about;
    private String profilePictureUrl;
    private User.OnlineStatus onlineStatus;
    private Boolean isActive;
    private LocalDateTime lastSeen;
    private LocalDateTime createdAt;

    public static UserResponse from(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .phoneNumber(u.getPhoneNumber())
                .displayName(u.getDisplayName())
                .about(u.getAbout())
                .profilePictureUrl(u.getProfilePictureUrl())
                .onlineStatus(u.getOnlineStatus())
                .isActive(u.getIsActive())
                .lastSeen(u.getLastSeen())
                .createdAt(u.getCreatedAt())
                .build();
    }
}