package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.dto.request.ShowRequest;
import com.example.Databases_System_Design_09.api.dto.response.SeatAvailabilityResponse;
import com.example.Databases_System_Design_09.api.entity.Movie;
import com.example.Databases_System_Design_09.api.entity.MovieShow;
import com.example.Databases_System_Design_09.api.entity.Screen;
import com.example.Databases_System_Design_09.api.entity.Seat;
import com.example.Databases_System_Design_09.api.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_09.api.repository.*;
import com.example.Databases_System_Design_09.api.service.ShowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;
    private final BookedSeatRepository bookedSeatRepository;

    public ShowServiceImpl(ShowRepository showRepository,
                           MovieRepository movieRepository,
                           ScreenRepository screenRepository,
                           SeatRepository seatRepository,
                           BookedSeatRepository bookedSeatRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
        this.bookedSeatRepository = bookedSeatRepository;
    }

    @Override
    public MovieShow createShow(ShowRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + request.getMovieId()));

        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found with id: " + request.getScreenId()));

        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        MovieShow show = new MovieShow();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());
        show.setPrice(request.getPrice());
        return showRepository.save(show);
    }

    @Override
    public List<MovieShow> getShowsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));
        return showRepository.findByMovie(movie);
    }

    @Override
    public List<MovieShow> getShowsByTheatre(Long theatreId) {
        return showRepository.findByTheatreId(theatreId);
    }

    @Override
    public MovieShow getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + id));
    }

    @Override
    public List<SeatAvailabilityResponse> getAvailableSeats(Long showId) {
        MovieShow show = getShowById(showId);

        // Get all seats for the screen
        List<Seat> allSeats = seatRepository.findByScreen(show.getScreen());

        // Get already booked seat IDs for this show (not cancelled)
        Set<Long> bookedSeatIds = bookedSeatRepository
                .findByShowAndStatusNot(show, "CANCELLED")
                .stream()
                .map(bs -> bs.getSeat().getId())
                .collect(Collectors.toSet());

        return allSeats.stream().map(seat -> {
            SeatAvailabilityResponse response = new SeatAvailabilityResponse();
            response.setSeatId(seat.getId());
            response.setSeatNumber(seat.getSeatNumber());
            response.setSeatType(seat.getSeatType());
            response.setAvailable(!bookedSeatIds.contains(seat.getId()));
            response.setPrice(show.getPrice());
            return response;
        }).collect(Collectors.toList());
    }
}