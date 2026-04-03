package com.example.Databases_System_Design_016.status.dto;

import com.example.Databases_System_Design_016.status.entity.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StatusResponse {

    private Long id;
    private Long userId;
    private String userDisplayName;
    private String userProfilePictureUrl;
    private Status.StatusType statusType;
    private String caption;
    private String mediaUrl;
    private String backgroundColor;
    private String fontStyle;
    private Long viewCount;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public static StatusResponse from(Status s) {
        return StatusResponse.builder()
                .id(s.getId())
                .userId(s.getUser().getId())
                .userDisplayName(s.getUser().getDisplayName())
                .userProfilePictureUrl(s.getUser().getProfilePictureUrl())
                .statusType(s.getStatusType())
                .caption(s.getCaption())
                .mediaUrl(s.getMediaUrl())
                .backgroundColor(s.getBackgroundColor())
                .fontStyle(s.getFontStyle())
                .viewCount(s.getViewCount())
                .isActive(s.getIsActive())
                .createdAt(s.getCreatedAt())
                .expiresAt(s.getExpiresAt())
                .build();
    }
}