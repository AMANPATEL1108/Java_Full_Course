package com.example.Databases_System_Design_09.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ScreenRequest {

    @NotBlank(message = "Screen name is required")
    private String name;

    @NotNull(message = "Total seats is required")
    @Min(value = 1, message = "Total seats must be at least 1")
    private Integer totalSeats;

    @NotNull(message = "Theatre ID is required")
    private Long theatreId;
}