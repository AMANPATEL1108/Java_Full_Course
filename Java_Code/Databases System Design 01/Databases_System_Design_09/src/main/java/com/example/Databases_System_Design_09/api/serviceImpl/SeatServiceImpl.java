package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.repository.SeatRepository;
import com.example.Databases_System_Design_09.api.service.SeatService;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

}
