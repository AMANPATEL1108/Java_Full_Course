package com.example.Databases_System_Design_09.api.service;

import com.example.Databases_System_Design_09.api.dto.request.BookingRequest;
import com.example.Databases_System_Design_09.api.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse bookTicket(BookingRequest request);
    List<BookingResponse> getBookingsByUser(Long userId);
    BookingResponse getBookingById(Long id);
    void cancelBooking(Long id);
}