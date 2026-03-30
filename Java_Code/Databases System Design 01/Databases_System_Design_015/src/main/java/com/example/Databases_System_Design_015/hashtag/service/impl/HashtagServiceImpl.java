package com.example.Databases_System_Design_015.hashtag.service.impl;

import com.example.Databases_System_Design_015.hashtag.dto.HashtagResponse;
import com.example.Databases_System_Design_015.hashtag.entity.Hashtag;
import com.example.Databases_System_Design_015.hashtag.repository.HashtagRepository;
import com.example.Databases_System_Design_015.hashtag.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;

    @Override
    public HashtagResponse createHashtag(String tag) {
        String normalized = tag.toLowerCase().replaceAll("[^a-z0-9_]", "");
        if (hashtagRepository.existsByTag(normalized)) {
            throw new RuntimeException("Hashtag already exists: #" + normalized);
        }
        Hashtag hashtag = Hashtag.builder()
                .tag(normalized)
                .usageCount(0L)
                .build();
        return HashtagResponse.from(hashtagRepository.save(hashtag));
    }

    @Override
    @Transactional(readOnly = true)
    public HashtagResponse getHashtagById(Long id) {
        return HashtagResponse.from(findHashtagOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public HashtagResponse getHashtagByTag(String tag) {
        Hashtag hashtag = hashtagRepository.findByTag(tag.toLowerCase())
                .orElseThrow(() -> new RuntimeException("Hashtag not found: #" + tag));
        return HashtagResponse.from(hashtag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HashtagResponse> getAllHashtags() {
        return hashtagRepository.findAll().stream()
                .map(HashtagResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HashtagResponse> getTrendingHashtags() {
        return hashtagRepository.findTrendingHashtags().stream()
                .map(HashtagResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HashtagResponse> searchHashtags(String keyword) {
        return hashtagRepository.findByTagContainingIgnoreCase(keyword).stream()
                .map(HashtagResponse::from).collect(Collectors.toList());
    }

    @Override
    public void deleteHashtag(Long id) {
        if (!hashtagRepository.existsById(id)) {
            throw new RuntimeException("Hashtag not found with id: " + id);
        }
        hashtagRepository.deleteById(id);
    }

    private Hashtag findHashtagOrThrow(Long id) {
        return hashtagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hashtag not found with id: " + id));
    }
}