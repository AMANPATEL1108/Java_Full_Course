package com.example.Databases_System_Design_015.comment.dto;

import com.example.Databases_System_Design_015.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponse {

    private Long id;
    private Long userId;
    private String username;
    private String userProfilePictureUrl;
    private Long tweetId;
    private String content;
    private Long parentCommentId;
    private Long likesCount;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public static CommentResponse from(Comment c) {
        return CommentResponse.builder()
                .id(c.getId())
                .userId(c.getUser().getId())
                .username(c.getUser().getUsername())
                .userProfilePictureUrl(c.getUser().getProfilePictureUrl())
                .tweetId(c.getTweet().getId())
                .content(c.getContent())
                .parentCommentId(c.getParentComment() != null ? c.getParentComment().getId() : null)
                .likesCount(c.getLikesCount())
                .isActive(c.getIsActive())
                .createdAt(c.getCreatedAt())
                .build();
    }
}