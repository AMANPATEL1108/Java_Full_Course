package com.example.Databases_System_Design_08.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "parking_spots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "floor_id", nullable = false)
    private Long floorId;

    @Column(nullable = false)
    private String type;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @OneToMany(mappedBy = "spot")
    private List<Ticket> tickets;
}