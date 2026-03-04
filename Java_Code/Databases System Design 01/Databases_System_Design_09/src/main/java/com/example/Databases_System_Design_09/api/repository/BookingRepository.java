package com.example.Databases_System_Design_09.api.repository;

import com.example.Databases_System_Design_09.api.entity.Booking;
import com.example.Databases_System_Design_09.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUser(User user);
}
