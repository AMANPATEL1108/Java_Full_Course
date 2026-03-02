package com.example.Databases_System_Design_09.api.repository;

import com.example.Databases_System_Design_09.api.entity.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<MovieShow,Long> {
}
