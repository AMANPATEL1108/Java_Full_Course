package com.example.Databases_System_Design_09.api.service;

import com.example.Databases_System_Design_09.api.dto.request.SeatRequest;
import com.example.Databases_System_Design_09.api.entity.Seat;

import java.util.List;

public interface SeatService {
    Seat createSeat(SeatRequest request);
    List<Seat> getSeatsByScreen(Long screenId);
}