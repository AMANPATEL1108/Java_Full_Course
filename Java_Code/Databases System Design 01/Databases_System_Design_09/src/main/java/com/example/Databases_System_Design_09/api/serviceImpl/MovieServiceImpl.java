package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.dto.request.MovieRequest;
import com.example.Databases_System_Design_09.api.entity.Movie;
import com.example.Databases_System_Design_09.api.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_09.api.repository.MovieRepository;
import com.example.Databases_System_Design_09.api.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(MovieRequest request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setDuration(request.getDuration());
        movie.setLanguage(request.getLanguage());
        movie.setGenre(request.getGenre());
        movie.setReleaseDate(request.getReleaseDate());
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    }

    @Override
    public Movie updateMovie(Long id, MovieRequest request) {
        Movie movie = getMovieById(id);
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setDuration(request.getDuration());
        movie.setLanguage(request.getLanguage());
        movie.setGenre(request.getGenre());
        movie.setReleaseDate(request.getReleaseDate());
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }
}