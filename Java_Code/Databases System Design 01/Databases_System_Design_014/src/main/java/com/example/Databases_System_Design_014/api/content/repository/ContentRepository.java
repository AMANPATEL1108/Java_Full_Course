package com.example.Databases_System_Design_014.api.content.repository;

import com.example.Databases_System_Design_014.api.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByContentType(Content.ContentType contentType);

    List<Content> findByGenre(Content.Genre genre);

    List<Content> findByIsAvailable(Boolean isAvailable);

    List<Content> findByTitleContainingIgnoreCase(String title);

    List<Content> findByReleaseYear(Integer releaseYear);

    @Query("SELECT c FROM Content c WHERE c.averageRating >= :minRating ORDER BY c.averageRating DESC")
    List<Content> findTopRatedContent(Double minRating);

    @Query("SELECT c FROM Content c ORDER BY c.viewCount DESC")
    List<Content> findMostWatchedContent();

    List<Content> findByGenreAndContentType(Content.Genre genre, Content.ContentType contentType);
}