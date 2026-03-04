package com.example.Databases_System_Design_09.api.service;

import com.example.Databases_System_Design_09.api.dto.request.MovieRequest;
import com.example.Databases_System_Design_09.api.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie createMovie(MovieRequest request);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie updateMovie(Long id, MovieRequest request);
    void deleteMovie(Long id);
}