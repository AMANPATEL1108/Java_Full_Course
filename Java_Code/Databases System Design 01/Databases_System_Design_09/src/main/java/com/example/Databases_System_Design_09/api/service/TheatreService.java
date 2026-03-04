package com.example.Databases_System_Design_09.api.service;

import com.example.Databases_System_Design_09.api.dto.request.TheatreRequest;
import com.example.Databases_System_Design_09.api.entity.Theatre;

import java.util.List;

public interface TheatreService {
    Theatre createTheatre(TheatreRequest request);
    List<Theatre> getAllTheatres();
    Theatre getTheatreById(Long id);
    Theatre updateTheatre(Long id, TheatreRequest request);
    void deleteTheatre(Long id);
}