package com.example.Databases_System_Design_014.api.review.service.impl;

import com.example.Databases_System_Design_014.api.content.entity.Content;
import com.example.Databases_System_Design_014.api.content.repository.ContentRepository;
import com.example.Databases_System_Design_014.api.review.dto.ReviewRequest;
import com.example.Databases_System_Design_014.api.review.dto.ReviewResponse;
import com.example.Databases_System_Design_014.api.review.entity.Review;
import com.example.Databases_System_Design_014.api.review.repository.ReviewRepository;
import com.example.Databases_System_Design_014.api.review.service.ReviewService;
import com.example.Databases_System_Design_014.api.user.entity.User;
import com.example.Databases_System_Design_014.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    public ReviewResponse addReview(ReviewRequest request) {
        if (reviewRepository.existsByUserIdAndContentId(request.getUserId(), request.getContentId())) {
            throw new RuntimeException("User has already reviewed this content");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        Content content = contentRepository.findById(request.getContentId())
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + request.getContentId()));

        Review review = Review.builder()
                .user(user)
                .content(content)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        ReviewResponse response = ReviewResponse.from(reviewRepository.save(review));

        // Recalculate and update average rating on content
        updateContentAverageRating(content);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponse getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        return ReviewResponse.from(review);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByContent(Long contentId) {
        return reviewRepository.findLatestReviewsByContentId(contentId).stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse updateReview(Long id, ReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        review.setRating(request.getRating());
        review.setComment(request.getComment());
        ReviewResponse response = ReviewResponse.from(reviewRepository.save(review));

        // Recalculate average rating
        updateContentAverageRating(review.getContent());

        return response;
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        Content content = review.getContent();
        reviewRepository.deleteById(id);
        updateContentAverageRating(content);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAverageRating(Long contentId) {
        Double avg = reviewRepository.findAverageRatingByContentId(contentId);
        return avg != null ? avg : 0.0;
    }

    // ─── Helper ───────────────────────────────────────────────────────────────
    private void updateContentAverageRating(Content content) {
        Double avg = reviewRepository.findAverageRatingByContentId(content.getId());
        content.setAverageRating(avg != null ? avg : 0.0);
        contentRepository.save(content);
    }
}