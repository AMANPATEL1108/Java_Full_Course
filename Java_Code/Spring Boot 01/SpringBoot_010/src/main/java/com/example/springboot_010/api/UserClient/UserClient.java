package com.example.springboot_010.api.UserClient;

import com.example.springboot_010.api.UserDto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDto getUserById(@PathVariable Long id);
}
