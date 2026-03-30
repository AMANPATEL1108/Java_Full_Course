package com.example.Databases_System_Design_015.tweet.service.impl;

import com.example.Databases_System_Design_015.follow.repository.FollowRepository;
import com.example.Databases_System_Design_015.hashtag.entity.Hashtag;
import com.example.Databases_System_Design_015.hashtag.repository.HashtagRepository;
import com.example.Databases_System_Design_015.tweet.dto.TweetRequest;
import com.example.Databases_System_Design_015.tweet.dto.TweetResponse;
import com.example.Databases_System_Design_015.tweet.entity.Tweet;
import com.example.Databases_System_Design_015.tweet.repository.TweetRepository;
import com.example.Databases_System_Design_015.tweet.service.TweetService;
import com.example.Databases_System_Design_015.user.entity.User;
import com.example.Databases_System_Design_015.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final HashtagRepository hashtagRepository;

    @Override
    public TweetResponse createTweet(TweetRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));

        Tweet tweet = Tweet.builder()
                .user(user)
                .content(request.getContent())
                .mediaUrl(request.getMediaUrl())
                .tweetType(request.getTweetType() != null ? request.getTweetType() : Tweet.TweetType.ORIGINAL)
                .isActive(true)
                .build();

        if (request.getParentTweetId() != null) {
            Tweet parent = tweetRepository.findById(request.getParentTweetId())
                    .orElseThrow(() -> new RuntimeException("Parent tweet not found: " + request.getParentTweetId()));
            tweet.setParentTweet(parent);
            if (tweet.getTweetType() == Tweet.TweetType.RETWEET) {
                parent.setRetweetsCount(parent.getRetweetsCount() + 1);
                tweetRepository.save(parent);
            }
        }

        List<Hashtag> hashtags = extractAndSaveHashtags(request.getContent());
        tweet.setHashtags(hashtags);

        Tweet saved = tweetRepository.save(tweet);

        user.setTweetsCount(user.getTweetsCount() + 1);
        userRepository.save(user);

        return TweetResponse.from(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TweetResponse getTweetById(Long id) {
        return TweetResponse.from(findTweetOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TweetResponse> getAllTweets() {
        return tweetRepository.findAll().stream()
                .map(TweetResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TweetResponse> getTweetsByUser(Long userId) {
        return tweetRepository.findByUserIdAndIsActiveOrderByCreatedAtDesc(userId, true)
                .stream().map(TweetResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TweetResponse> getLatestTweets() {
        return tweetRepository.findLatestTweets().stream()
                .map(TweetResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TweetResponse> getTrendingTweets() {
        return tweetRepository.findTrendingTweets().stream()
                .map(TweetResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TweetResponse> getTimeline(Long userId) {
        List<Long> followingIds = followRepository.findFollowingIdsByUserId(userId);
        followingIds.add(userId);
        return tweetRepository.findTimelineByUserIds(followingIds).stream()
                .map(TweetResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TweetResponse> searchTweets(String keyword) {
        return tweetRepository.findByContentContainingIgnoreCaseAndIsActive(keyword, true)
                .stream().map(TweetResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TweetResponse> getTweetsByHashtag(String tag) {
        return tweetRepository.findByHashtag(tag).stream()
                .map(TweetResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TweetResponse> getRetweetsOfTweet(Long tweetId) {
        return tweetRepository.findByParentTweetIdAndIsActive(tweetId, true)
                .stream().map(TweetResponse::from).collect(Collectors.toList());
    }

    @Override
    public TweetResponse updateTweet(Long id, TweetRequest request) {
        Tweet tweet = findTweetOrThrow(id);
        tweet.setContent(request.getContent());
        tweet.setMediaUrl(request.getMediaUrl());
        List<Hashtag> hashtags = extractAndSaveHashtags(request.getContent());
        tweet.setHashtags(hashtags);
        return TweetResponse.from(tweetRepository.save(tweet));
    }

    @Override
    public void incrementViewCount(Long id) {
        Tweet tweet = findTweetOrThrow(id);
        tweet.setViewsCount(tweet.getViewsCount() + 1);
        tweetRepository.save(tweet);
    }

    @Override
    public void deleteTweet(Long id) {
        Tweet tweet = findTweetOrThrow(id);
        tweet.setIsActive(false);
        tweetRepository.save(tweet);
        User user = tweet.getUser();
        if (user.getTweetsCount() > 0) {
            user.setTweetsCount(user.getTweetsCount() - 1);
            userRepository.save(user);
        }
    }

    private Tweet findTweetOrThrow(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found with id: " + id));
    }

    private List<Hashtag> extractAndSaveHashtags(String content) {
        List<Hashtag> hashtags = new ArrayList<>();
        Matcher matcher = Pattern.compile("#(\\w+)").matcher(content);
        while (matcher.find()) {
            String tag = matcher.group(1).toLowerCase();
            Hashtag hashtag = hashtagRepository.findByTag(tag).orElseGet(() ->
                    hashtagRepository.save(Hashtag.builder().tag(tag).usageCount(0L).build()));
            hashtag.setUsageCount(hashtag.getUsageCount() + 1);
            hashtagRepository.save(hashtag);
            hashtags.add(hashtag);
        }
        return hashtags;
    }
}