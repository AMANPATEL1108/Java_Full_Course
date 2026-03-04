package com.example.Databases_System_Design_09.api.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class BookingResponse {
    private Long id;
    private UUID uuid;
    private String userName;
    private String movieTitle;
    private String screenName;
    private String theatreName;
    private java.time.LocalDateTime showStartTime;
    private java.util.List<String> seatNumbers;
    private Double totalPrice;
    private String status;
    private String paymentStatus;
}