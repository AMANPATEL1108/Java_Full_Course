package com.example.Databases_System_Design_015.tweet.dto;

import com.example.Databases_System_Design_015.tweet.entity.Tweet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TweetRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Content is required")
    @Size(max = 280, message = "Tweet cannot exceed 280 characters")
    private String content;

    private String mediaUrl;

    private Tweet.TweetType tweetType = Tweet.TweetType.ORIGINAL;

    private Long parentTweetId;
}