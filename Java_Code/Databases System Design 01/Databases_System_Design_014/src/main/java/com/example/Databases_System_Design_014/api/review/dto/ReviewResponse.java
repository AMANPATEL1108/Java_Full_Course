package com.example.Databases_System_Design_014.api.review.dto;

import com.example.Databases_System_Design_014.api.review.entity.Review;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewResponse {

    private Long id;
    private Long userId;
    private String userFullName;
    private Long contentId;
    private String contentTitle;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

    public static ReviewResponse from(Review r) {
        return ReviewResponse.builder()
                .id(r.getId())
                .userId(r.getUser().getId())
                .userFullName(r.getUser().getFullName())
                .contentId(r.getContent().getId())
                .contentTitle(r.getContent().getTitle())
                .rating(r.getRating())
                .comment(r.getComment())
                .createdAt(r.getCreatedAt())
                .build();
    }
}