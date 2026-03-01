package com.example.Databases_System_Design_08.controller;

import com.example.Databases_System_Design_08.entity.Ticket;
import com.example.Databases_System_Design_08.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingLotController {

    private final ParkingService parkingService;

    @PostMapping("/entry")
    public Ticket entry(
            @RequestParam String plateNumber,
            @RequestParam String vehicleType) {

        return parkingService.parkVehicle(plateNumber, vehicleType);
    }

    @PostMapping("/exit/{ticketId}")
    public Ticket exit(@PathVariable Long ticketId) {

        return parkingService.exitVehicle(ticketId);
    }

    @PostMapping("/pay/{ticketId}")
    public Ticket pay(@PathVariable Long ticketId) {

        return parkingService.pay(ticketId);
    }
}