package com.example.Databases_System_Design_08.service;

import com.example.Databases_System_Design_08.entity.ParkingSpot;

import java.util.List;

public interface ParkingSpotService {

    ParkingSpot createSpot(Long floorId, String type);

    List<ParkingSpot> getAllSpots();

    List<ParkingSpot> getAvailableSpots(String type);
}