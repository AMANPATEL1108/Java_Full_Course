package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.dto.request.ShowRequest;
import com.example.Databases_System_Design_09.api.dto.response.ApiResponse;
import com.example.Databases_System_Design_09.api.dto.response.SeatAvailabilityResponse;
import com.example.Databases_System_Design_09.api.entity.MovieShow;
import com.example.Databases_System_Design_09.api.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    /**
     * POST /shows
     * ADMIN only - Create a show
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<MovieShow>> createShow(@Valid @RequestBody ShowRequest request) {
        MovieShow show = showService.createShow(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Show created successfully", show));
    }

    /**
     * GET /shows/movie/{movieId}
     * Get all shows for a movie
     */
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<ApiResponse<List<MovieShow>>> getShowsByMovie(@PathVariable Long movieId) {
        List<MovieShow> shows = showService.getShowsByMovie(movieId);
        return ResponseEntity.ok(ApiResponse.success("Shows fetched successfully", shows));
    }

    /**
     * GET /shows/theatre/{theatreId}
     * Get all shows for a theatre
     */
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<ApiResponse<List<MovieShow>>> getShowsByTheatre(@PathVariable Long theatreId) {
        List<MovieShow> shows = showService.getShowsByTheatre(theatreId);
        return ResponseEntity.ok(ApiResponse.success("Shows fetched successfully", shows));
    }

    /**
     * GET /shows/{showId}
     * Get show by ID
     */
    @GetMapping("/{showId}")
    public ResponseEntity<ApiResponse<MovieShow>> getShowById(@PathVariable Long showId) {
        MovieShow show = showService.getShowById(showId);
        return ResponseEntity.ok(ApiResponse.success("Show fetched successfully", show));
    }

    /**
     * GET /shows/{showId}/seats
     * Step 2 of booking flow - Get seat availability for a show
     */
    @GetMapping("/{showId}/seats")
    public ResponseEntity<ApiResponse<List<SeatAvailabilityResponse>>> getAvailableSeats(@PathVariable Long showId) {
        List<SeatAvailabilityResponse> seats = showService.getAvailableSeats(showId);
        return ResponseEntity.ok(ApiResponse.success("Seat availability fetched", seats));
    }
}