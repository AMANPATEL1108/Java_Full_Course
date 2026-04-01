package com.example.Databases_System_Design_015.like.controller;

import com.example.Databases_System_Design_015.like.dto.LikeResponse;
import com.example.Databases_System_Design_015.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // POST http://localhost:8080/api/v1/likes/like?userId=1&tweetId=1
    @PostMapping("/like")
    public ResponseEntity<LikeResponse> likeTweet(@RequestParam Long userId,
                                                  @RequestParam Long tweetId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(likeService.likeTweet(userId, tweetId));
    }

    // DELETE http://localhost:8080/api/v1/likes/unlike?userId=1&tweetId=1
    @DeleteMapping("/unlike")
    public ResponseEntity<Void> unlikeTweet(@RequestParam Long userId,
                                            @RequestParam Long tweetId) {
        likeService.unlikeTweet(userId, tweetId);
        return ResponseEntity.noContent().build();
    }

    // GET http://localhost:8080/api/v1/likes/check?userId=1&tweetId=1
    @GetMapping("/check")
    public ResponseEntity<Boolean> isLiked(@RequestParam Long userId,
                                           @RequestParam Long tweetId) {
        return ResponseEntity.ok(likeService.isLiked(userId, tweetId));
    }

    // GET http://localhost:8080/api/v1/likes/tweet/{tweetId}
    @GetMapping("/tweet/{tweetId}")
    public ResponseEntity<List<LikeResponse>> getLikesByTweet(@PathVariable Long tweetId) {
        return ResponseEntity.ok(likeService.getLikesByTweet(tweetId));
    }

    // GET http://localhost:8080/api/v1/likes/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeResponse>> getLikesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(likeService.getLikesByUser(userId));
    }

    // GET http://localhost:8080/api/v1/likes/tweet/{tweetId}/count
    @GetMapping("/tweet/{tweetId}/count")
    public ResponseEntity<Long> getLikesCount(@PathVariable Long tweetId) {
        return ResponseEntity.ok(likeService.getLikesCount(tweetId));
    }
}