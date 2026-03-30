package com.example.Databases_System_Design_015.user.dto;

import com.example.Databases_System_Design_015.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String displayName;
    private String bio;
    private String profilePictureUrl;
    private String bannerUrl;
    private String location;
    private String website;
    private Boolean isVerified;
    private Boolean isActive;
    private Long followersCount;
    private Long followingCount;
    private Long tweetsCount;
    private LocalDateTime createdAt;

    public static UserResponse from(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .username(u.getUsername())
                .email(u.getEmail())
                .displayName(u.getDisplayName())
                .bio(u.getBio())
                .profilePictureUrl(u.getProfilePictureUrl())
                .bannerUrl(u.getBannerUrl())
                .location(u.getLocation())
                .website(u.getWebsite())
                .isVerified(u.getIsVerified())
                .isActive(u.getIsActive())
                .followersCount(u.getFollowersCount())
                .followingCount(u.getFollowingCount())
                .tweetsCount(u.getTweetsCount())
                .createdAt(u.getCreatedAt())
                .build();
    }
}