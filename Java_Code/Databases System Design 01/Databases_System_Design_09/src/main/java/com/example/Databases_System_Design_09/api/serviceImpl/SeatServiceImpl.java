package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.dto.request.SeatRequest;
import com.example.Databases_System_Design_09.api.entity.Screen;
import com.example.Databases_System_Design_09.api.entity.Seat;
import com.example.Databases_System_Design_09.api.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_09.api.repository.ScreenRepository;
import com.example.Databases_System_Design_09.api.repository.SeatRepository;
import com.example.Databases_System_Design_09.api.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final ScreenRepository screenRepository;

    public SeatServiceImpl(SeatRepository seatRepository, ScreenRepository screenRepository) {
        this.seatRepository = seatRepository;
        this.screenRepository = screenRepository;
    }

    @Override
    public Seat createSeat(SeatRequest request) {
        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found with id: " + request.getScreenId()));

        Seat seat = new Seat();
        seat.setSeatNumber(request.getSeatNumber());
        seat.setSeatType(request.getSeatType());
        seat.setScreen(screen);
        return seatRepository.save(seat);
    }

    @Override
    public List<Seat> getSeatsByScreen(Long screenId) {
        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found with id: " + screenId));
        return seatRepository.findByScreen(screen);
    }
}