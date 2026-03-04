package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.dto.request.SeatRequest;
import com.example.Databases_System_Design_09.api.dto.response.ApiResponse;
import com.example.Databases_System_Design_09.api.entity.Seat;
import com.example.Databases_System_Design_09.api.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    /** POST /seats - ADMIN only */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Seat>> createSeat(@Valid @RequestBody SeatRequest request) {
        Seat seat = seatService.createSeat(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Seat created successfully", seat));
    }

    /** GET /seats/screen/{screenId} */
    @GetMapping("/screen/{screenId}")
    public ResponseEntity<ApiResponse<List<Seat>>> getSeatsByScreen(@PathVariable Long screenId) {
        List<Seat> seats = seatService.getSeatsByScreen(screenId);
        return ResponseEntity.ok(ApiResponse.success("Seats fetched successfully", seats));
    }
}