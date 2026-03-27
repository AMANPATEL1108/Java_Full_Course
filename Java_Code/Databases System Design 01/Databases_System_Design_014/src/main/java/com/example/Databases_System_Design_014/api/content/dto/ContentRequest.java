package com.example.Databases_System_Design_014.api.content.dto;

import com.example.Databases_System_Design_014.api.content.entity.Content;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContentRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Content type is required")
    private Content.ContentType contentType;

    @NotNull(message = "Genre is required")
    private Content.Genre genre;

    private String maturityRating;

    private Integer releaseYear;

    private LocalDate releaseDate;

    private Integer durationMinutes;

    private Integer totalSeasons;

    private Integer totalEpisodes;

    private String thumbnailUrl;

    private String trailerUrl;

    private String streamUrl;
}