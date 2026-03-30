package com.example.Databases_System_Design_015.like.dto;

import com.example.Databases_System_Design_015.like.entity.Like;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LikeResponse {

    private Long id;
    private Long userId;
    private String username;
    private Long tweetId;
    private String tweetContent;
    private LocalDateTime likedAt;

    public static LikeResponse from(Like l) {
        return LikeResponse.builder()
                .id(l.getId())
                .userId(l.getUser().getId())
                .username(l.getUser().getUsername())
                .tweetId(l.getTweet().getId())
                .tweetContent(l.getTweet().getContent())
                .likedAt(l.getLikedAt())
                .build();
    }
}