package com.example.springboot_02.api.dayEightyTwo;

import jakarta.validation.constraints.*;

public class DayEightyTwo_82 {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must be at most 60")
    private Integer age;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    // getters and setters
}
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @PostMapping
//    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest user) {
//        return ResponseEntity.ok("User created successfully");
//    }
//}
