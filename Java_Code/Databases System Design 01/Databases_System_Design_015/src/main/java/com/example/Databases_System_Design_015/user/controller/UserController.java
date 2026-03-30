package com.example.Databases_System_Design_015.user.controller;

import com.example.Databases_System_Design_015.user.dto.UserRequest;
import com.example.Databases_System_Design_015.user.dto.UserResponse;
import com.example.Databases_System_Design_015.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // POST http://localhost:8080/api/v1/users
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    // GET http://localhost:8080/api/v1/users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET http://localhost:8080/api/v1/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // GET http://localhost:8080/api/v1/users/username/{username}
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    // GET http://localhost:8080/api/v1/users/search?keyword=john
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam String keyword) {
        return ResponseEntity.ok(userService.searchUsers(keyword));
    }

    // GET http://localhost:8080/api/v1/users/top
    @GetMapping("/top")
    public ResponseEntity<List<UserResponse>> getTopUsers() {
        return ResponseEntity.ok(userService.getTopUsers());
    }

    // PUT http://localhost:8080/api/v1/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    // PATCH http://localhost:8080/api/v1/users/{id}/deactivate
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE http://localhost:8080/api/v1/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}