package com.example.Databases_System_Design_014.api.review.repository;

import com.example.Databases_System_Design_014.api.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByContentId(Long contentId);

    List<Review> findByUserId(Long userId);

    Optional<Review> findByUserIdAndContentId(Long userId, Long contentId);

    boolean existsByUserIdAndContentId(Long userId, Long contentId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.content.id = :contentId")
    Double findAverageRatingByContentId(Long contentId);

    @Query("SELECT r FROM Review r WHERE r.content.id = :contentId ORDER BY r.createdAt DESC")
    List<Review> findLatestReviewsByContentId(Long contentId);
}