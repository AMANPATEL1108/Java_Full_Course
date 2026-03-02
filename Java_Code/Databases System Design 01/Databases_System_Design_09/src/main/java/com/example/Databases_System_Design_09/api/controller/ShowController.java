package com.example.Databases_System_Design_09.api.controller;

import com.example.Databases_System_Design_09.api.service.ShowService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    private final ShowService showService;

    ShowController(ShowService showService) {
        this.showService = showService;
    }

}
