package com.example.Databases_System_Design_09.api.dto.response;

import lombok.Data;

@Data
public class SeatAvailabilityResponse {
    private Long seatId;
    private String seatNumber;
    private String seatType;
    private boolean available;
    private Double price;
}