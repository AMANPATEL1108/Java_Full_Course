package com.example.springboot_010.api.userController;

import com.example.springboot_010.api.UserDto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return new UserDto(id, "John Doe");
    }
}
