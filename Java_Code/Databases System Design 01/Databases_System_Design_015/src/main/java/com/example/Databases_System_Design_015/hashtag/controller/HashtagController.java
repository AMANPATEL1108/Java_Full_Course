package com.example.Databases_System_Design_015.hashtag.controller;

import com.example.Databases_System_Design_015.hashtag.dto.HashtagResponse;
import com.example.Databases_System_Design_015.hashtag.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hashtags")
@RequiredArgsConstructor
public class HashtagController {

    private final HashtagService hashtagService;

    // POST http://localhost:8080/api/v1/hashtags?tag=springboot
    @PostMapping
    public ResponseEntity<HashtagResponse> createHashtag(@RequestParam String tag) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hashtagService.createHashtag(tag));
    }

    // GET http://localhost:8080/api/v1/hashtags
    @GetMapping
    public ResponseEntity<List<HashtagResponse>> getAllHashtags() {
        return ResponseEntity.ok(hashtagService.getAllHashtags());
    }

    // GET http://localhost:8080/api/v1/hashtags/{id}
    @GetMapping("/{id}")
    public ResponseEntity<HashtagResponse> getHashtagById(@PathVariable Long id) {
        return ResponseEntity.ok(hashtagService.getHashtagById(id));
    }

    // GET http://localhost:8080/api/v1/hashtags/tag/{tag}
    @GetMapping("/tag/{tag}")
    public ResponseEntity<HashtagResponse> getHashtagByTag(@PathVariable String tag) {
        return ResponseEntity.ok(hashtagService.getHashtagByTag(tag));
    }

    // GET http://localhost:8080/api/v1/hashtags/trending
    @GetMapping("/trending")
    public ResponseEntity<List<HashtagResponse>> getTrendingHashtags() {
        return ResponseEntity.ok(hashtagService.getTrendingHashtags());
    }

    // GET http://localhost:8080/api/v1/hashtags/search?keyword=spring
    @GetMapping("/search")
    public ResponseEntity<List<HashtagResponse>> searchHashtags(@RequestParam String keyword) {
        return ResponseEntity.ok(hashtagService.searchHashtags(keyword));
    }

    // DELETE http://localhost:8080/api/v1/hashtags/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHashtag(@PathVariable Long id) {
        hashtagService.deleteHashtag(id);
        return ResponseEntity.noContent().build();
    }
}