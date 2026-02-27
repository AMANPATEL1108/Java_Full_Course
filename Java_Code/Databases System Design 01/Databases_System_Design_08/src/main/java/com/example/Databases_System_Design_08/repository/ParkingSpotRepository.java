package com.example.Databases_System_Design_08.repository;

import com.example.Databases_System_Design_08.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {
    Optional<ParkingSpot> findFirstByTypeAndIsAvailableTrue(String type);

    @Modifying
    @Query("UPDATE ParkingSpot p SET p.isAvailable = false WHERE p.id = :id AND p.isAvailable = true")
    int lockSpot(Long id);

    @Modifying
    @Query("UPDATE ParkingSpot p SET p.isAvailable = true WHERE p.id = :id")
    void freeSpot(Long id);
}
