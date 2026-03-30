package com.example.Databases_System_Design_015.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Tweet ID is required")
    private Long tweetId;

    @NotBlank(message = "Content is required")
    @Size(max = 280, message = "Comment cannot exceed 280 characters")
    private String content;

    private Long parentCommentId;
}