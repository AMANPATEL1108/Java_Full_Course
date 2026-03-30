package com.example.Databases_System_Design_015.like.service.impl;

import com.example.Databases_System_Design_015.like.dto.LikeResponse;
import com.example.Databases_System_Design_015.like.entity.Like;
import com.example.Databases_System_Design_015.like.repository.LikeRepository;
import com.example.Databases_System_Design_015.like.service.LikeService;
import com.example.Databases_System_Design_015.tweet.entity.Tweet;
import com.example.Databases_System_Design_015.tweet.repository.TweetRepository;
import com.example.Databases_System_Design_015.user.entity.User;
import com.example.Databases_System_Design_015.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @Override
    public LikeResponse likeTweet(Long userId, Long tweetId) {
        if (likeRepository.existsByUserIdAndTweetId(userId, tweetId)) {
            throw new RuntimeException("Tweet already liked");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found: " + tweetId));

        Like like = Like.builder().user(user).tweet(tweet).build();
        Like saved = likeRepository.save(like);

        tweet.setLikesCount(tweet.getLikesCount() + 1);
        tweetRepository.save(tweet);

        return LikeResponse.from(saved);
    }

    @Override
    public void unlikeTweet(Long userId, Long tweetId) {
        if (!likeRepository.existsByUserIdAndTweetId(userId, tweetId)) {
            throw new RuntimeException("Tweet not liked yet");
        }
        likeRepository.deleteByUserIdAndTweetId(userId, tweetId);

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found: " + tweetId));
        if (tweet.getLikesCount() > 0) {
            tweet.setLikesCount(tweet.getLikesCount() - 1);
            tweetRepository.save(tweet);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isLiked(Long userId, Long tweetId) {
        return likeRepository.existsByUserIdAndTweetId(userId, tweetId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LikeResponse> getLikesByTweet(Long tweetId) {
        return likeRepository.findByTweetId(tweetId).stream()
                .map(LikeResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LikeResponse> getLikesByUser(Long userId) {
        return likeRepository.findByUserId(userId).stream()
                .map(LikeResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Long getLikesCount(Long tweetId) {
        return likeRepository.countByTweetId(tweetId);
    }
}