package com.example.Databases_System_Design_09.api.repository;

import com.example.Databases_System_Design_09.api.entity.Movie;
import com.example.Databases_System_Design_09.api.entity.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<MovieShow,Long> {
    List<MovieShow> findByMovie(Movie movie);

    @Query("SELECT s FROM MovieShow s WHERE s.screen.theatre.id = :theatreId")
    List<MovieShow> findByTheatreId(Long theatreId);
}
