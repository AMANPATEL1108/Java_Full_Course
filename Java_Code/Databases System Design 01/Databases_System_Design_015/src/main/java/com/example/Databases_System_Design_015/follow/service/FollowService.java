package com.example.Databases_System_Design_015.follow.service;

import com.example.Databases_System_Design_015.follow.dto.FollowResponse;
import com.example.Databases_System_Design_015.user.dto.UserResponse;

import java.util.List;

public interface FollowService {

    FollowResponse followUser(Long followerId, Long followingId);

    void unfollowUser(Long followerId, Long followingId);

    boolean isFollowing(Long followerId, Long followingId);

    List<UserResponse> getFollowers(Long userId);

    List<UserResponse> getFollowing(Long userId);

    Long getFollowersCount(Long userId);

    Long getFollowingCount(Long userId);
}