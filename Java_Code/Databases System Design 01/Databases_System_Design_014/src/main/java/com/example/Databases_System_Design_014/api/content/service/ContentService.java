package com.example.Databases_System_Design_014.api.content.service;

import com.example.Databases_System_Design_014.api.content.dto.ContentRequest;
import com.example.Databases_System_Design_014.api.content.dto.ContentResponse;
import com.example.Databases_System_Design_014.api.content.entity.Content;

import java.util.List;

public interface ContentService {

    ContentResponse createContent(ContentRequest request);

    ContentResponse getContentById(Long id);

    List<ContentResponse> getAllContent();

    List<ContentResponse> getAvailableContent();

    List<ContentResponse> getContentByType(Content.ContentType type);

    List<ContentResponse> getContentByGenre(Content.Genre genre);

    List<ContentResponse> searchByTitle(String title);

    List<ContentResponse> getTopRatedContent(Double minRating);

    List<ContentResponse> getMostWatchedContent();

    ContentResponse updateContent(Long id, ContentRequest request);

    void incrementViewCount(Long id);

    void toggleAvailability(Long id);

    void deleteContent(Long id);
}