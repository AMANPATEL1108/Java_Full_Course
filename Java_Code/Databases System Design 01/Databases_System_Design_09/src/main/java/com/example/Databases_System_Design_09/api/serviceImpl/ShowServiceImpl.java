package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.repository.ShowRepository;
import com.example.Databases_System_Design_09.api.service.ShowService;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;

    ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

}
