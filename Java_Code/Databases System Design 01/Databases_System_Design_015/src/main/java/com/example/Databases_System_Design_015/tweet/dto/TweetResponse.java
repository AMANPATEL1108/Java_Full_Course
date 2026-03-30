package com.example.Databases_System_Design_015.tweet.dto;

import com.example.Databases_System_Design_015.tweet.entity.Tweet;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TweetResponse {

    private Long id;
    private Long userId;
    private String username;
    private String userDisplayName;
    private String profilePictureUrl;
    private String content;
    private String mediaUrl;
    private Tweet.TweetType tweetType;
    private Long parentTweetId;
    private Long likesCount;
    private Long retweetsCount;
    private Long commentsCount;
    private Long viewsCount;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public static TweetResponse from(Tweet t) {
        return TweetResponse.builder()
                .id(t.getId())
                .userId(t.getUser().getId())
                .username(t.getUser().getUsername())
                .userDisplayName(t.getUser().getDisplayName())
                .profilePictureUrl(t.getUser().getProfilePictureUrl())
                .content(t.getContent())
                .mediaUrl(t.getMediaUrl())
                .tweetType(t.getTweetType())
                .parentTweetId(t.getParentTweet() != null ? t.getParentTweet().getId() : null)
                .likesCount(t.getLikesCount())
                .retweetsCount(t.getRetweetsCount())
                .commentsCount(t.getCommentsCount())
                .viewsCount(t.getViewsCount())
                .isActive(t.getIsActive())
                .createdAt(t.getCreatedAt())
                .build();
    }
}