package com.example.Databases_System_Design_09.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booked_seats", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"show_id", "seat_id"})
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookedSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private MovieShow show;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    // LOCKED, CONFIRMED, CANCELLED
    @Column(nullable = false)
    private String status;
}