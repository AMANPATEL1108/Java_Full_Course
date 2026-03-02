package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.service.ScreenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/screen")
public class ScreenController {

    private final ScreenService screenService;

    ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

}
