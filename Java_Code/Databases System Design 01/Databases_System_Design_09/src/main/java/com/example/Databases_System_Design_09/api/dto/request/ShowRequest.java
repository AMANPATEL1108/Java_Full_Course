package com.example.Databases_System_Design_09.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ShowRequest {

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @NotNull(message = "Screen ID is required")
    private Long screenId;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private Double price;
}