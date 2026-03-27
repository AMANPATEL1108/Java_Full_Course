package com.example.Databases_System_Design_014.api.review.service;

import com.example.Databases_System_Design_014.api.review.dto.ReviewRequest;
import com.example.Databases_System_Design_014.api.review.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse addReview(ReviewRequest request);

    ReviewResponse getReviewById(Long id);

    List<ReviewResponse> getReviewsByContent(Long contentId);

    List<ReviewResponse> getReviewsByUser(Long userId);

    ReviewResponse updateReview(Long id, ReviewRequest request);

    void deleteReview(Long id);

    Double getAverageRating(Long contentId);
}