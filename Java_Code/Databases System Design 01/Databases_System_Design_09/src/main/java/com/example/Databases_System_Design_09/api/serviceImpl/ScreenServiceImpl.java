package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.dto.request.ScreenRequest;
import com.example.Databases_System_Design_09.api.entity.Screen;
import com.example.Databases_System_Design_09.api.entity.Theatre;
import com.example.Databases_System_Design_09.api.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_09.api.repository.ScreenRepository;
import com.example.Databases_System_Design_09.api.repository.TheatreRepository;
import com.example.Databases_System_Design_09.api.service.ScreenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    public ScreenServiceImpl(ScreenRepository screenRepository, TheatreRepository theatreRepository) {
        this.screenRepository = screenRepository;
        this.theatreRepository = theatreRepository;
    }

    @Override
    public Screen createScreen(ScreenRequest request) {
        Theatre theatre = theatreRepository.findById(request.getTheatreId())
                .orElseThrow(() -> new ResourceNotFoundException("Theatre not found with id: " + request.getTheatreId()));

        Screen screen = new Screen();
        screen.setName(request.getName());
        screen.setTotalSeats(request.getTotalSeats());
        screen.setTheatre(theatre);
        return screenRepository.save(screen);
    }

    @Override
    public List<Screen> getScreensByTheatre(Long theatreId) {
        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new ResourceNotFoundException("Theatre not found with id: " + theatreId));
        return screenRepository.findByTheatre(theatre);
    }

    @Override
    public Screen getScreenById(Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found with id: " + id));
    }
}