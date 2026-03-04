package com.example.Databases_System_Design_09.api.repository;

import com.example.Databases_System_Design_09.api.entity.Screen;
import com.example.Databases_System_Design_09.api.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen,Long> {
    List<Screen> findByTheatre(Theatre theatre);
}
