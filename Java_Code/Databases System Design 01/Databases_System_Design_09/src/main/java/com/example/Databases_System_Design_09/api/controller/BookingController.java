package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.service.BookingService;
import com.example.Databases_System_Design_09.api.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

}
