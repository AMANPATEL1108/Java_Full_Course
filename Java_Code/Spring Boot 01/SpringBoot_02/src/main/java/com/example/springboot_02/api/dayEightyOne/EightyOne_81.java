package com.example.springboot_02.api.dayEightyOne;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")    // base URL (optional)
public class EightyOne_81 {

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable int id) {
        if (id != 1) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        return "John Doe";
    }
}
