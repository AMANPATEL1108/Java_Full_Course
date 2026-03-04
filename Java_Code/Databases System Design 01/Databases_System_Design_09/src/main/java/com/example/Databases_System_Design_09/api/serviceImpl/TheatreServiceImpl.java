package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.dto.request.TheatreRequest;
import com.example.Databases_System_Design_09.api.entity.Theatre;
import com.example.Databases_System_Design_09.api.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_09.api.repository.TheatreRepository;
import com.example.Databases_System_Design_09.api.service.TheatreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public Theatre createTheatre(TheatreRequest request) {
        Theatre theatre = new Theatre();
        theatre.setName(request.getName());
        theatre.setCity(request.getCity());
        theatre.setAddress(request.getAddress());
        return theatreRepository.save(theatre);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theatre not found with id: " + id));
    }

    @Override
    public Theatre updateTheatre(Long id, TheatreRequest request) {
        Theatre theatre = getTheatreById(id);
        theatre.setName(request.getName());
        theatre.setCity(request.getCity());
        theatre.setAddress(request.getAddress());
        return theatreRepository.save(theatre);
    }

    @Override
    public void deleteTheatre(Long id) {
        Theatre theatre = getTheatreById(id);
        theatreRepository.delete(theatre);
    }
}