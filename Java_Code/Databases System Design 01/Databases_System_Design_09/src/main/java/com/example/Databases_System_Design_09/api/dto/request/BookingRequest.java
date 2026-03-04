package com.example.Databases_System_Design_09.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class BookingRequest {

    @NotNull(message = "Show ID is required")
    private Long showId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "At least one seat must be selected")
    private List<Long> seatIds;

    @NotNull(message = "Payment method is required")
    private String paymentMethod;
}