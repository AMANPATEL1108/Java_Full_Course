package com.example.Databases_System_Design_015.hashtag.service;

import com.example.Databases_System_Design_015.hashtag.dto.HashtagResponse;

import java.util.List;

public interface HashtagService {

    HashtagResponse createHashtag(String tag);

    HashtagResponse getHashtagById(Long id);

    HashtagResponse getHashtagByTag(String tag);

    List<HashtagResponse> getAllHashtags();

    List<HashtagResponse> getTrendingHashtags();

    List<HashtagResponse> searchHashtags(String keyword);

    void deleteHashtag(Long id);
}