package com.example.Databases_System_Design_015.like.repository;

import com.example.Databases_System_Design_015.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndTweetId(Long userId, Long tweetId);

    boolean existsByUserIdAndTweetId(Long userId, Long tweetId);

    void deleteByUserIdAndTweetId(Long userId, Long tweetId);

    List<Like> findByTweetId(Long tweetId);

    List<Like> findByUserId(Long userId);

    Long countByTweetId(Long tweetId);
}