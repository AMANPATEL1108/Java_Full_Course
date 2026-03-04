// ScreenController.java
package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.dto.request.ScreenRequest;
import com.example.Databases_System_Design_09.api.dto.response.ApiResponse;
import com.example.Databases_System_Design_09.api.entity.Screen;
import com.example.Databases_System_Design_09.api.service.ScreenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screens")
public class ScreenController {

    private final ScreenService screenService;

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    /** POST /screens - ADMIN only */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Screen>> createScreen(@Valid @RequestBody ScreenRequest request) {
        Screen screen = screenService.createScreen(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Screen created successfully", screen));
    }

    /** GET /screens/theatre/{theatreId} */
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<ApiResponse<List<Screen>>> getScreensByTheatre(@PathVariable Long theatreId) {
        List<Screen> screens = screenService.getScreensByTheatre(theatreId);
        return ResponseEntity.ok(ApiResponse.success("Screens fetched successfully", screens));
    }

    /** GET /screens/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Screen>> getScreenById(@PathVariable Long id) {
        Screen screen = screenService.getScreenById(id);
        return ResponseEntity.ok(ApiResponse.success("Screen fetched successfully", screen));
    }
}