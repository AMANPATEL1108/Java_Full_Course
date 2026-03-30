package com.example.Databases_System_Design_015.tweet.controller;

import com.example.Databases_System_Design_015.tweet.dto.TweetRequest;
import com.example.Databases_System_Design_015.tweet.dto.TweetResponse;
import com.example.Databases_System_Design_015.tweet.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tweets")
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    // POST http://localhost:8080/api/v1/tweets
    @PostMapping
    public ResponseEntity<TweetResponse> createTweet(@Valid @RequestBody TweetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tweetService.createTweet(request));
    }

    // GET http://localhost:8080/api/v1/tweets
    @GetMapping
    public ResponseEntity<List<TweetResponse>> getAllTweets() {
        return ResponseEntity.ok(tweetService.getAllTweets());
    }

    // GET http://localhost:8080/api/v1/tweets/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TweetResponse> getTweetById(@PathVariable Long id) {
        return ResponseEntity.ok(tweetService.getTweetById(id));
    }

    // GET http://localhost:8080/api/v1/tweets/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TweetResponse>> getTweetsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(tweetService.getTweetsByUser(userId));
    }

    // GET http://localhost:8080/api/v1/tweets/latest
    @GetMapping("/latest")
    public ResponseEntity<List<TweetResponse>> getLatestTweets() {
        return ResponseEntity.ok(tweetService.getLatestTweets());
    }

    // GET http://localhost:8080/api/v1/tweets/trending
    @GetMapping("/trending")
    public ResponseEntity<List<TweetResponse>> getTrendingTweets() {
        return ResponseEntity.ok(tweetService.getTrendingTweets());
    }

    // GET http://localhost:8080/api/v1/tweets/timeline/{userId}
    @GetMapping("/timeline/{userId}")
    public ResponseEntity<List<TweetResponse>> getTimeline(@PathVariable Long userId) {
        return ResponseEntity.ok(tweetService.getTimeline(userId));
    }

    // GET http://localhost:8080/api/v1/tweets/search?keyword=hello
    @GetMapping("/search")
    public ResponseEntity<List<TweetResponse>> searchTweets(@RequestParam String keyword) {
        return ResponseEntity.ok(tweetService.searchTweets(keyword));
    }

    // GET http://localhost:8080/api/v1/tweets/hashtag/{tag}
    @GetMapping("/hashtag/{tag}")
    public ResponseEntity<List<TweetResponse>> getTweetsByHashtag(@PathVariable String tag) {
        return ResponseEntity.ok(tweetService.getTweetsByHashtag(tag));
    }

    // GET http://localhost:8080/api/v1/tweets/{id}/retweets
    @GetMapping("/{id}/retweets")
    public ResponseEntity<List<TweetResponse>> getRetweets(@PathVariable Long id) {
        return ResponseEntity.ok(tweetService.getRetweetsOfTweet(id));
    }

    // PUT http://localhost:8080/api/v1/tweets/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TweetResponse> updateTweet(@PathVariable Long id,
                                                     @Valid @RequestBody TweetRequest request) {
        return ResponseEntity.ok(tweetService.updateTweet(id, request));
    }

    // PATCH http://localhost:8080/api/v1/tweets/{id}/view
    @PatchMapping("/{id}/view")
    public ResponseEntity<Void> incrementView(@PathVariable Long id) {
        tweetService.incrementViewCount(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE http://localhost:8080/api/v1/tweets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {
        tweetService.deleteTweet(id);
        return ResponseEntity.noContent().build();
    }
}