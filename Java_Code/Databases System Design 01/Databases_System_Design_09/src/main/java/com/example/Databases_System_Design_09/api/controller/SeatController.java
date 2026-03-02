package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.service.SeatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

}
