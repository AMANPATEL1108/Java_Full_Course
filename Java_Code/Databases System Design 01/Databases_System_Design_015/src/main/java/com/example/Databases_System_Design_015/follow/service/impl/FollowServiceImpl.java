package com.example.Databases_System_Design_015.follow.service.impl;

import com.example.Databases_System_Design_015.follow.dto.FollowResponse;
import com.example.Databases_System_Design_015.follow.entity.Follow;
import com.example.Databases_System_Design_015.follow.repository.FollowRepository;
import com.example.Databases_System_Design_015.follow.service.FollowService;
import com.example.Databases_System_Design_015.user.dto.UserResponse;
import com.example.Databases_System_Design_015.user.entity.User;
import com.example.Databases_System_Design_015.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public FollowResponse followUser(Long followerId, Long followingId) {
        if (followerId.equals(followingId)) {
            throw new RuntimeException("User cannot follow themselves");
        }
        if (followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new RuntimeException("Already following this user");
        }
        User follower = findUserOrThrow(followerId);
        User following = findUserOrThrow(followingId);

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();
        Follow saved = followRepository.save(follow);

        follower.setFollowingCount(follower.getFollowingCount() + 1);
        following.setFollowersCount(following.getFollowersCount() + 1);
        userRepository.save(follower);
        userRepository.save(following);

        return FollowResponse.from(saved);
    }

    @Override
    public void unfollowUser(Long followerId, Long followingId) {
        if (!followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new RuntimeException("Not following this user");
        }
        followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);

        User follower = findUserOrThrow(followerId);
        User following = findUserOrThrow(followingId);
        if (follower.getFollowingCount() > 0) follower.setFollowingCount(follower.getFollowingCount() - 1);
        if (following.getFollowersCount() > 0) following.setFollowersCount(following.getFollowersCount() - 1);
        userRepository.save(follower);
        userRepository.save(following);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isFollowing(Long followerId, Long followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getFollowers(Long userId) {
        return followRepository.findByFollowingId(userId).stream()
                .map(f -> UserResponse.from(f.getFollower()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getFollowing(Long userId) {
        return followRepository.findByFollowerId(userId).stream()
                .map(f -> UserResponse.from(f.getFollowing()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Long getFollowersCount(Long userId) {
        return followRepository.countByFollowingId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getFollowingCount(Long userId) {
        return followRepository.countByFollowerId(userId);
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}