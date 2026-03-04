package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.dto.request.TheatreRequest;
import com.example.Databases_System_Design_09.api.dto.response.ApiResponse;
import com.example.Databases_System_Design_09.api.entity.Theatre;
import com.example.Databases_System_Design_09.api.service.TheatreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    /**
     * POST /theatres
     * ADMIN only
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Theatre>> createTheatre(@Valid @RequestBody TheatreRequest request) {
        Theatre theatre = theatreService.createTheatre(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Theatre created successfully", theatre));
    }

    /**
     * GET /theatres
     * Public
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Theatre>>> getAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return ResponseEntity.ok(ApiResponse.success("Theatres fetched successfully", theatres));
    }

    /**
     * GET /theatres/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Theatre>> getTheatreById(@PathVariable Long id) {
        Theatre theatre = theatreService.getTheatreById(id);
        return ResponseEntity.ok(ApiResponse.success("Theatre fetched successfully", theatre));
    }

    /**
     * PUT /theatres/{id}
     * ADMIN only
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Theatre>> updateTheatre(@PathVariable Long id,
                                                              @Valid @RequestBody TheatreRequest request) {
        Theatre theatre = theatreService.updateTheatre(id, request);
        return ResponseEntity.ok(ApiResponse.success("Theatre updated successfully", theatre));
    }

    /**
     * DELETE /theatres/{id}
     * ADMIN only
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
        return ResponseEntity.ok(ApiResponse.success("Theatre deleted successfully", null));
    }
}