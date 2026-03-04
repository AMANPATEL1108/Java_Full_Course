package com.example.Databases_System_Design_09.api.service;

import com.example.Databases_System_Design_09.api.dto.request.ShowRequest;
import com.example.Databases_System_Design_09.api.dto.response.SeatAvailabilityResponse;
import com.example.Databases_System_Design_09.api.entity.MovieShow;

import java.util.List;

public interface ShowService {
    MovieShow createShow(ShowRequest request);
    List<MovieShow> getShowsByMovie(Long movieId);
    List<MovieShow> getShowsByTheatre(Long theatreId);
    MovieShow getShowById(Long id);
    List<SeatAvailabilityResponse> getAvailableSeats(Long showId);
}