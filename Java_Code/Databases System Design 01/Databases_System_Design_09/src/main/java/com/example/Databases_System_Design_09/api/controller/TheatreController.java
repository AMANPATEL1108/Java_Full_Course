package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.service.TheatreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    private final TheatreService theatreService;

    TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }
}
