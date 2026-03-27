package com.example.Databases_System_Design_014.api.content.dto;

import com.example.Databases_System_Design_014.api.content.entity.Content;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ContentResponse {

    private Long id;
    private String title;
    private String description;
    private Content.ContentType contentType;
    private Content.Genre genre;
    private String maturityRating;
    private Integer releaseYear;
    private LocalDate releaseDate;
    private Integer durationMinutes;
    private Integer totalSeasons;
    private Integer totalEpisodes;
    private String thumbnailUrl;
    private String trailerUrl;
    private String streamUrl;
    private Double averageRating;
    private Long viewCount;
    private Boolean isAvailable;
    private LocalDateTime createdAt;

    public static ContentResponse from(Content c) {
        return ContentResponse.builder()
                .id(c.getId())
                .title(c.getTitle())
                .description(c.getDescription())
                .contentType(c.getContentType())
                .genre(c.getGenre())
                .maturityRating(c.getMaturityRating())
                .releaseYear(c.getReleaseYear())
                .releaseDate(c.getReleaseDate())
                .durationMinutes(c.getDurationMinutes())
                .totalSeasons(c.getTotalSeasons())
                .totalEpisodes(c.getTotalEpisodes())
                .thumbnailUrl(c.getThumbnailUrl())
                .trailerUrl(c.getTrailerUrl())
                .streamUrl(c.getStreamUrl())
                .averageRating(c.getAverageRating())
                .viewCount(c.getViewCount())
                .isAvailable(c.getIsAvailable())
                .createdAt(c.getCreatedAt())
                .build();
    }
}