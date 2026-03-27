package com.example.Databases_System_Design_014.api.watchlist.controller;

import com.example.Databases_System_Design_014.api.watchlist.dto.WatchlistResponse;
import com.example.Databases_System_Design_014.api.watchlist.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping("/user/{userId}/content/{contentId}")
    public ResponseEntity<WatchlistResponse> add(@PathVariable Long userId,
                                                 @PathVariable Long contentId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(watchlistService.addToWatchlist(userId, contentId));
    }

    @DeleteMapping("/user/{userId}/content/{contentId}")
    public ResponseEntity<Void> remove(@PathVariable Long userId,
                                       @PathVariable Long contentId) {
        watchlistService.removeFromWatchlist(userId, contentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WatchlistResponse>> getUserWatchlist(@PathVariable Long userId) {
        return ResponseEntity.ok(watchlistService.getUserWatchlist(userId));
    }

    @GetMapping("/user/{userId}/content/{contentId}/check")
    public ResponseEntity<Boolean> isInWatchlist(@PathVariable Long userId,
                                                 @PathVariable Long contentId) {
        return ResponseEntity.ok(watchlistService.isInWatchlist(userId, contentId));
    }

    @DeleteMapping("/user/{userId}/clear")
    public ResponseEntity<Void> clearWatchlist(@PathVariable Long userId) {
        watchlistService.clearWatchlist(userId);
        return ResponseEntity.noContent().build();
    }
}