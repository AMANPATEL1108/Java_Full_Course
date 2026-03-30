package com.example.Databases_System_Design_015.tweet.service;

import com.example.Databases_System_Design_015.tweet.dto.TweetRequest;
import com.example.Databases_System_Design_015.tweet.dto.TweetResponse;
import com.example.Databases_System_Design_015.tweet.entity.Tweet;

import java.util.List;

public interface TweetService {

    TweetResponse createTweet(TweetRequest request);

    TweetResponse getTweetById(Long id);

    List<TweetResponse> getAllTweets();

    List<TweetResponse> getTweetsByUser(Long userId);

    List<TweetResponse> getLatestTweets();

    List<TweetResponse> getTrendingTweets();

    List<TweetResponse> getTimeline(Long userId);

    List<TweetResponse> searchTweets(String keyword);

    List<TweetResponse> getTweetsByHashtag(String tag);

    List<TweetResponse> getRetweetsOfTweet(Long tweetId);

    TweetResponse updateTweet(Long id, TweetRequest request);

    void incrementViewCount(Long id);

    void deleteTweet(Long id);
}