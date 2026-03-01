package com.example.Databases_System_Design_08.service;

import com.example.Databases_System_Design_08.entity.Ticket;

public interface ParkingService {
    Ticket parkVehicle(String plateNumber, String vehicleType);

    Ticket exitVehicle(Long ticketId);

    Ticket pay(Long ticketId);
}
