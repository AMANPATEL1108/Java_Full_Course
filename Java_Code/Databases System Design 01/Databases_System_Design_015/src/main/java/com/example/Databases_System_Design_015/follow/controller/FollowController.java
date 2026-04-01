package com.example.Databases_System_Design_015.follow.controller;

import com.example.Databases_System_Design_015.follow.dto.FollowResponse;
import com.example.Databases_System_Design_015.follow.service.FollowService;
import com.example.Databases_System_Design_015.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // POST http://localhost:8080/api/v1/follows/follow?followerId=1&followingId=2
    @PostMapping("/follow")
    public ResponseEntity<FollowResponse> followUser(@RequestParam Long followerId,
                                                     @RequestParam Long followingId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(followService.followUser(followerId, followingId));
    }

    // DELETE http://localhost:8080/api/v1/follows/unfollow?followerId=1&followingId=2
    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unfollowUser(@RequestParam Long followerId,
                                             @RequestParam Long followingId) {
        followService.unfollowUser(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    // GET http://localhost:8080/api/v1/follows/check?followerId=1&followingId=2
    @GetMapping("/check")
    public ResponseEntity<Boolean> isFollowing(@RequestParam Long followerId,
                                               @RequestParam Long followingId) {
        return ResponseEntity.ok(followService.isFollowing(followerId, followingId));
    }

    // GET http://localhost:8080/api/v1/follows/{userId}/followers
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<UserResponse>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }

    // GET http://localhost:8080/api/v1/follows/{userId}/following
    @GetMapping("/{userId}/following")
    public ResponseEntity<List<UserResponse>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowing(userId));
    }

    // GET http://localhost:8080/api/v1/follows/{userId}/followers/count
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<Long> getFollowersCount(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowersCount(userId));
    }

    // GET http://localhost:8080/api/v1/follows/{userId}/following/count
    @GetMapping("/{userId}/following/count")
    public ResponseEntity<Long> getFollowingCount(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowingCount(userId));
    }
}