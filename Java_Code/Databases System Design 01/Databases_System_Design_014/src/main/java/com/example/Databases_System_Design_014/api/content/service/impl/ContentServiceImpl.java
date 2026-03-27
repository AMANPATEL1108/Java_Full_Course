package com.example.Databases_System_Design_014.api.content.service.impl;

import com.example.Databases_System_Design_014.api.content.dto.ContentRequest;
import com.example.Databases_System_Design_014.api.content.dto.ContentResponse;
import com.example.Databases_System_Design_014.api.content.entity.Content;
import com.example.Databases_System_Design_014.api.content.repository.ContentRepository;
import com.example.Databases_System_Design_014.api.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Override
    public ContentResponse createContent(ContentRequest request) {
        Content content = Content.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .contentType(request.getContentType())
                .genre(request.getGenre())
                .maturityRating(request.getMaturityRating())
                .releaseYear(request.getReleaseYear())
                .releaseDate(request.getReleaseDate())
                .durationMinutes(request.getDurationMinutes())
                .totalSeasons(request.getTotalSeasons())
                .totalEpisodes(request.getTotalEpisodes())
                .thumbnailUrl(request.getThumbnailUrl())
                .trailerUrl(request.getTrailerUrl())
                .streamUrl(request.getStreamUrl())
                .isAvailable(true)
                .build();
        return ContentResponse.from(contentRepository.save(content));
    }

    @Override
    @Transactional(readOnly = true)
    public ContentResponse getContentById(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + id));
        return ContentResponse.from(content);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentResponse> getAllContent() {
        return contentRepository.findAll().stream()
                .map(ContentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentResponse> getAvailableContent() {
        return contentRepository.findByIsAvailable(true).stream()
                .map(ContentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentResponse> getContentByType(Content.ContentType type) {
        return contentRepository.findByContentType(type).stream()
                .map(ContentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentResponse> getContentByGenre(Content.Genre genre) {
        return contentRepository.findByGenre(genre).stream()
                .map(ContentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentResponse> searchByTitle(String title) {
        return contentRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(ContentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentResponse> getTopRatedContent(Double minRating) {
        return contentRepository.findTopRatedContent(minRating).stream()
                .map(ContentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentResponse> getMostWatchedContent() {
        return contentRepository.findMostWatchedContent().stream()
                .map(ContentResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ContentResponse updateContent(Long id, ContentRequest request) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + id));

        content.setTitle(request.getTitle());
        content.setDescription(request.getDescription());
        content.setContentType(request.getContentType());
        content.setGenre(request.getGenre());
        content.setMaturityRating(request.getMaturityRating());
        content.setReleaseYear(request.getReleaseYear());
        content.setReleaseDate(request.getReleaseDate());
        content.setDurationMinutes(request.getDurationMinutes());
        content.setTotalSeasons(request.getTotalSeasons());
        content.setTotalEpisodes(request.getTotalEpisodes());
        content.setThumbnailUrl(request.getThumbnailUrl());
        content.setTrailerUrl(request.getTrailerUrl());
        content.setStreamUrl(request.getStreamUrl());

        return ContentResponse.from(contentRepository.save(content));
    }

    @Override
    public void incrementViewCount(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + id));
        content.setViewCount(content.getViewCount() + 1);
        contentRepository.save(content);
    }

    @Override
    public void toggleAvailability(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + id));
        content.setIsAvailable(!content.getIsAvailable());
        contentRepository.save(content);
    }

    @Override
    public void deleteContent(Long id) {
        if (!contentRepository.existsById(id)) {
            throw new RuntimeException("Content not found with id: " + id);
        }
        contentRepository.deleteById(id);
    }
}