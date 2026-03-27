package com.example.Databases_System_Design_014.api.review.controller;

import com.example.Databases_System_Design_014.api.review.dto.ReviewRequest;
import com.example.Databases_System_Design_014.api.review.dto.ReviewResponse;
import com.example.Databases_System_Design_014.api.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // POST http://localhost:8080/api/v1/reviews
    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(request));
    }

    // GET http://localhost:8080/api/v1/reviews/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    // GET http://localhost:8080/api/v1/reviews/content/{contentId}
    @GetMapping("/content/{contentId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByContent(@PathVariable Long contentId) {
        return ResponseEntity.ok(reviewService.getReviewsByContent(contentId));
    }

    // GET http://localhost:8080/api/v1/reviews/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }

    // GET http://localhost:8080/api/v1/reviews/content/{contentId}/average
    @GetMapping("/content/{contentId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long contentId) {
        return ResponseEntity.ok(reviewService.getAverageRating(contentId));
    }

    // PUT http://localhost:8080/api/v1/reviews/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.updateReview(id, request));
    }

    // DELETE http://localhost:8080/api/v1/reviews/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}