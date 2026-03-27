package com.example.Databases_System_Design_014.api.watchlist.service.impl;

import com.example.Databases_System_Design_014.api.content.entity.Content;
import com.example.Databases_System_Design_014.api.content.repository.ContentRepository;
import com.example.Databases_System_Design_014.api.user.entity.User;
import com.example.Databases_System_Design_014.api.user.repository.UserRepository;
import com.example.Databases_System_Design_014.api.watchlist.dto.WatchlistResponse;
import com.example.Databases_System_Design_014.api.watchlist.entity.Watchlist;
import com.example.Databases_System_Design_014.api.watchlist.repository.WatchlistRepository;
import com.example.Databases_System_Design_014.api.watchlist.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    public WatchlistResponse addToWatchlist(Long userId, Long contentId) {
        if (watchlistRepository.existsByUserIdAndContentId(userId, contentId)) {
            throw new RuntimeException("Content already in watchlist");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + contentId));

        Watchlist watchlist = Watchlist.builder()
                .user(user)
                .content(content)
                .build();

        return WatchlistResponse.from(watchlistRepository.save(watchlist));
    }

    @Override
    public void removeFromWatchlist(Long userId, Long contentId) {
        if (!watchlistRepository.existsByUserIdAndContentId(userId, contentId)) {
            throw new RuntimeException("Content not found in watchlist");
        }
        watchlistRepository.deleteByUserIdAndContentId(userId, contentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WatchlistResponse> getUserWatchlist(Long userId) {
        return watchlistRepository.findByUserId(userId).stream()
                .map(WatchlistResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isInWatchlist(Long userId, Long contentId) {
        return watchlistRepository.existsByUserIdAndContentId(userId, contentId);
    }

    @Override
    public void clearWatchlist(Long userId) {
        List<Watchlist> items = watchlistRepository.findByUserId(userId);
        watchlistRepository.deleteAll(items);
    }
}