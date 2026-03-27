package com.example.Databases_System_Design_014.api.content.controller;

import com.example.Databases_System_Design_014.api.content.dto.ContentRequest;
import com.example.Databases_System_Design_014.api.content.dto.ContentResponse;
import com.example.Databases_System_Design_014.api.content.entity.Content;
import com.example.Databases_System_Design_014.api.content.service.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<ContentResponse> createContent(@Valid @RequestBody ContentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contentService.createContent(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> getContentById(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.getContentById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContentResponse>> getAllContent() {
        return ResponseEntity.ok(contentService.getAllContent());
    }

    @GetMapping("/available")
    public ResponseEntity<List<ContentResponse>> getAvailableContent() {
        return ResponseEntity.ok(contentService.getAvailableContent());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ContentResponse>> getByType(@PathVariable Content.ContentType type) {
        return ResponseEntity.ok(contentService.getContentByType(type));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<ContentResponse>> getByGenre(@PathVariable Content.Genre genre) {
        return ResponseEntity.ok(contentService.getContentByGenre(genre));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContentResponse>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(contentService.searchByTitle(title));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<ContentResponse>> getTopRated(
            @RequestParam(defaultValue = "4.0") Double minRating) {
        return ResponseEntity.ok(contentService.getTopRatedContent(minRating));
    }

    @GetMapping("/most-watched")
    public ResponseEntity<List<ContentResponse>> getMostWatched() {
        return ResponseEntity.ok(contentService.getMostWatchedContent());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentResponse> updateContent(@PathVariable Long id,
                                                         @Valid @RequestBody ContentRequest request) {
        return ResponseEntity.ok(contentService.updateContent(id, request));
    }

    @PatchMapping("/{id}/view")
    public ResponseEntity<Void> incrementView(@PathVariable Long id) {
        contentService.incrementViewCount(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/toggle-availability")
    public ResponseEntity<Void> toggleAvailability(@PathVariable Long id) {
        contentService.toggleAvailability(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}