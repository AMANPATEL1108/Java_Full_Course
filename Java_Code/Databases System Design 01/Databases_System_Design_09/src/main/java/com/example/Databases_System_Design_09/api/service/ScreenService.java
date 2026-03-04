package com.example.Databases_System_Design_09.api.service;

import com.example.Databases_System_Design_09.api.dto.request.ScreenRequest;
import com.example.Databases_System_Design_09.api.entity.Screen;

import java.util.List;

public interface ScreenService {
    Screen createScreen(ScreenRequest request);
    List<Screen> getScreensByTheatre(Long theatreId);
    Screen getScreenById(Long id);
}