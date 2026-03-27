package com.example.Databases_System_Design_014.api.watchlist.dto;

import com.example.Databases_System_Design_014.api.watchlist.entity.Watchlist;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WatchlistResponse {

    private Long id;
    private Long userId;
    private Long contentId;
    private String contentTitle;
    private String contentType;
    private String thumbnailUrl;
    private LocalDateTime addedAt;

    public static WatchlistResponse from(Watchlist w) {
        return WatchlistResponse.builder()
                .id(w.getId())
                .userId(w.getUser().getId())
                .contentId(w.getContent().getId())
                .contentTitle(w.getContent().getTitle())
                .contentType(w.getContent().getContentType().name())
                .thumbnailUrl(w.getContent().getThumbnailUrl())
                .addedAt(w.getAddedAt())
                .build();
    }
}