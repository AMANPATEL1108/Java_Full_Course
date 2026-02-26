package com.example.Databases_System_Design_08.repository;

import com.example.Databases_System_Design_08.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
