package com.example.Databases_System_Design_08.controller;

import com.example.Databases_System_Design_08.entity.ParkingSpot;
import com.example.Databases_System_Design_08.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spots")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService spotService;

    @PostMapping("/create")
    public ParkingSpot createSpot(
            @RequestParam Long floorId,
            @RequestParam String type) {

        return spotService.createSpot(floorId, type);
    }

    @GetMapping("/all")
    public List<ParkingSpot> getAllSpots() {
        return spotService.getAllSpots();
    }

    @GetMapping("/available")
    public List<ParkingSpot> getAvailableSpots(
            @RequestParam String type) {

        return spotService.getAvailableSpots(type);
    }
}