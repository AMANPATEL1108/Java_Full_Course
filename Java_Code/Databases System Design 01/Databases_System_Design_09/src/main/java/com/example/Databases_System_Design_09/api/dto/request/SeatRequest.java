package com.example.Databases_System_Design_09.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SeatRequest {

    @NotBlank(message = "Seat number is required")
    private String seatNumber;

    @NotBlank(message = "Seat type is required")
    private String seatType; // STANDARD, PREMIUM, VIP

    @NotNull(message = "Screen ID is required")
    private Long screenId;
}