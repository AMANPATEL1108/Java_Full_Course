package com.example.Databases_System_Design_08.repository;

import com.example.Databases_System_Design_08.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle> findByPlateNumber(String plateNumber);
}
