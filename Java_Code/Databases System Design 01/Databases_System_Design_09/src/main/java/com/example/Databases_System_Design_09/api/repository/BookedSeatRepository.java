package com.example.Databases_System_Design_09.api.repository;

import com.example.Databases_System_Design_09.api.entity.BookedSeat;
import com.example.Databases_System_Design_09.api.entity.MovieShow;
import com.example.Databases_System_Design_09.api.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedSeatRepository extends JpaRepository<BookedSeat, Long> {
    boolean existsByShowAndSeatAndStatusNot(MovieShow show, Seat seat, String status);
    List<BookedSeat> findByShow(MovieShow show);
    List<BookedSeat> findByShowAndStatusNot(MovieShow show, String status);
}