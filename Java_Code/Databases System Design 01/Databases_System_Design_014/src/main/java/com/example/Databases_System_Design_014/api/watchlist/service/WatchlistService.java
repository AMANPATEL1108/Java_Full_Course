package com.example.Databases_System_Design_014.api.watchlist.service;

import com.example.Databases_System_Design_014.api.watchlist.dto.WatchlistResponse;

import java.util.List;

public interface WatchlistService {

    WatchlistResponse addToWatchlist(Long userId, Long contentId);

    void removeFromWatchlist(Long userId, Long contentId);

    List<WatchlistResponse> getUserWatchlist(Long userId);

    boolean isInWatchlist(Long userId, Long contentId);

    void clearWatchlist(Long userId);
}