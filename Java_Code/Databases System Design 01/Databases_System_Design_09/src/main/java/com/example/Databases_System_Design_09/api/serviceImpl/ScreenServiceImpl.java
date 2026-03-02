package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.repository.ScreenRepository;
import com.example.Databases_System_Design_09.api.service.ScreenService;
import org.springframework.stereotype.Service;

@Service
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;

    ScreenServiceImpl(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }
}
