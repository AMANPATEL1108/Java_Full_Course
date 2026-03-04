package com.example.Databases_System_Design_09.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MovieRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be positive")
    private Integer duration;

    @NotBlank(message = "Language is required")
    private String language;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;
}