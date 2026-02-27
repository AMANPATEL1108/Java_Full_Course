package com.example.Databases_System_Design_08.serviceImpl;

import com.example.Databases_System_Design_08.entity.ParkingSpot;
import com.example.Databases_System_Design_08.repository.ParkingSpotRepository;
import com.example.Databases_System_Design_08.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository spotRepository;

    @Override
    public ParkingSpot createSpot(Long floorId, String type) {

        ParkingSpot spot = new ParkingSpot();
        spot.setFloorId(floorId);
        spot.setType(type);
        spot.setIsAvailable(true);

        return spotRepository.save(spot);
    }

    @Override
    public List<ParkingSpot> getAllSpots() {
        return spotRepository.findAll();
    }

    @Override
    public List<ParkingSpot> getAvailableSpots(String type) {
        return spotRepository.findAll()
                .stream()
                .filter(s -> s.getType().equalsIgnoreCase(type) && Boolean.TRUE.equals(s.getIsAvailable()))
                .toList();
    }
}