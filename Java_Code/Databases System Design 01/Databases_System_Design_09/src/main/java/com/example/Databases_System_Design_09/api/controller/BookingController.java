package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.dto.request.BookingRequest;
import com.example.Databases_System_Design_09.api.dto.response.ApiResponse;
import com.example.Databases_System_Design_09.api.dto.response.BookingResponse;
import com.example.Databases_System_Design_09.api.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * POST /bookings
     * Authenticated user - Book tickets
     * Step 3 of booking flow
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<BookingResponse>> bookTicket(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Booking confirmed successfully", response));
    }

    /**
     * GET /bookings/user/{userId}
     * Get all bookings for a user
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookingsByUser(@PathVariable Long userId) {
        List<BookingResponse> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(ApiResponse.success("Bookings fetched successfully", bookings));
    }

    /**
     * GET /bookings/{id}
     * Get booking by ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<BookingResponse>> getBookingById(@PathVariable Long id) {
        BookingResponse booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(ApiResponse.success("Booking fetched successfully", booking));
    }

    /**
     * DELETE /bookings/{id}/cancel
     * Cancel a booking
     */
    @PatchMapping("/{id}/cancel")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Void>> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled successfully", null));
    }
}