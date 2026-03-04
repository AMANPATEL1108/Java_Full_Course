package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.dto.request.MovieRequest;
import com.example.Databases_System_Design_09.api.dto.response.ApiResponse;
import com.example.Databases_System_Design_09.api.entity.Movie;
import com.example.Databases_System_Design_09.api.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * POST /movies
     * ADMIN only - Add a new movie
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Movie>> createMovie(@Valid @RequestBody MovieRequest request) {
        Movie movie = movieService.createMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Movie created successfully", movie));
    }

    /**
     * GET /movies
     * Public - Get all movies
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Movie>>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(ApiResponse.success("Movies fetched successfully", movies));
    }

    /**
     * GET /movies/{id}
     * Public - Get movie by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Movie>> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(ApiResponse.success("Movie fetched successfully", movie));
    }

    /**
     * PUT /movies/{id}
     * ADMIN only - Update a movie
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Movie>> updateMovie(@PathVariable Long id,
                                                          @Valid @RequestBody MovieRequest request) {
        Movie movie = movieService.updateMovie(id, request);
        return ResponseEntity.ok(ApiResponse.success("Movie updated successfully", movie));
    }

    /**
     * DELETE /movies/{id}
     * ADMIN only - Delete a movie
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(ApiResponse.success("Movie deleted successfully", null));
    }
}