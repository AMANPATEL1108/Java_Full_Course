package com.example.Databases_System_Design_09.api.repository;

import com.example.Databases_System_Design_09.api.entity.Booking;
import com.example.Databases_System_Design_09.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByBooking(Booking booking);
}
