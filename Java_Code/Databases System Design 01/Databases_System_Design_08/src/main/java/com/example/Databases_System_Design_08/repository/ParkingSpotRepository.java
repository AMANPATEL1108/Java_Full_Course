package com.example.Databases_System_Design_08.repository;

import com.example.Databases_System_Design_08.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {
}
