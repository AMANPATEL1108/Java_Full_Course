package com.example.Databases_System_Design_08.serviceImpl;

import com.example.Databases_System_Design_08.entity.*;
import com.example.Databases_System_Design_08.repository.*;
import com.example.Databases_System_Design_08.service.ParkingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSpotRepository spotRepository;
    private final TicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public Ticket parkVehicle(String plateNumber, String vehicleType) {

        Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber)
                .orElseGet(() -> {
                    Vehicle v = new Vehicle();
                    v.setPlateNumber(plateNumber);
                    v.setType(vehicleType);
                    return vehicleRepository.save(v);
                });

        ParkingSpot spot = spotRepository
                .findFirstByTypeAndIsAvailableTrue(vehicleType)
                .orElseThrow(() -> new RuntimeException("No spot available"));

        int updated = spotRepository.lockSpot(spot.getId());
        if (updated == 0) {
            throw new RuntimeException("Spot already taken. Try again.");
        }

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setSpot(spot);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus("ACTIVE");

        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public Ticket exitVehicle(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if ("PAID".equals(ticket.getStatus())) {
            throw new RuntimeException("Already paid");
        }

        LocalDateTime exitTime = LocalDateTime.now();
        long hours = Duration.between(ticket.getEntryTime(), exitTime).toHours();
        if (hours == 0) hours = 1;

        double amount = hours * 20.0;

        ticket.setExitTime(exitTime);
        ticket.setAmount(amount);
        ticket.setStatus("PAID");

        spotRepository.freeSpot(ticket.getSpot().getId());

        return ticketRepository.save(ticket);
    }
}