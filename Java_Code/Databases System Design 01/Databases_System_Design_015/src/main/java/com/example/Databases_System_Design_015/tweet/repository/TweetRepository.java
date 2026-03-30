package com.example.Databases_System_Design_015.tweet.repository;

import com.example.Databases_System_Design_015.tweet.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByUserIdAndIsActiveOrderByCreatedAtDesc(Long userId, Boolean isActive);

    List<Tweet> findByTweetTypeAndIsActiveOrderByCreatedAtDesc(
            Tweet.TweetType tweetType, Boolean isActive);

    List<Tweet> findByContentContainingIgnoreCaseAndIsActive(String keyword, Boolean isActive);

    @Query("SELECT t FROM Tweet t WHERE t.isActive = true ORDER BY t.createdAt DESC")
    List<Tweet> findLatestTweets();

    @Query("SELECT t FROM Tweet t WHERE t.isActive = true ORDER BY t.likesCount DESC")
    List<Tweet> findTrendingTweets();

    @Query("SELECT t FROM Tweet t WHERE t.user.id IN :userIds AND t.isActive = true ORDER BY t.createdAt DESC")
    List<Tweet> findTimelineByUserIds(List<Long> userIds);

    List<Tweet> findByParentTweetIdAndIsActive(Long parentTweetId, Boolean isActive);

    @Query("SELECT t FROM Tweet t JOIN t.hashtags h WHERE h.tag = :tag AND t.isActive = true ORDER BY t.createdAt DESC")
    List<Tweet> findByHashtag(String tag);
}