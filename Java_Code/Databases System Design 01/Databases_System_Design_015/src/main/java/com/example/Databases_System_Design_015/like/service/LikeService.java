package com.example.Databases_System_Design_015.like.service;

import com.example.Databases_System_Design_015.like.dto.LikeResponse;

import java.util.List;

public interface LikeService {

    LikeResponse likeTweet(Long userId, Long tweetId);

    void unlikeTweet(Long userId, Long tweetId);

    boolean isLiked(Long userId, Long tweetId);

    List<LikeResponse> getLikesByTweet(Long tweetId);

    List<LikeResponse> getLikesByUser(Long userId);

    Long getLikesCount(Long tweetId);
}