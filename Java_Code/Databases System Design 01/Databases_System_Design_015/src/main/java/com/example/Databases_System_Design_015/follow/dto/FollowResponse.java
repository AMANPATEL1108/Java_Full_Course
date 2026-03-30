package com.example.Databases_System_Design_015.follow.dto;

import com.example.Databases_System_Design_015.follow.entity.Follow;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FollowResponse {

    private Long id;
    private Long followerId;
    private String followerUsername;
    private Long followingId;
    private String followingUsername;
    private LocalDateTime followedAt;

    public static FollowResponse from(Follow f) {
        return FollowResponse.builder()
                .id(f.getId())
                .followerId(f.getFollower().getId())
                .followerUsername(f.getFollower().getUsername())
                .followingId(f.getFollowing().getId())
                .followingUsername(f.getFollowing().getUsername())
                .followedAt(f.getFollowedAt())
                .build();
    }
}