package com.example.Databases_System_Design_016.user.controller;

import com.example.Databases_System_Design_016.user.dto.UserRequest;
import com.example.Databases_System_Design_016.user.dto.UserResponse;
import com.example.Databases_System_Design_016.user.entity.User;
import com.example.Databases_System_Design_016.user.service.UserService;
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
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
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

    // GET http://localhost:8080/api/v1/users/phone/{phoneNumber}
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<UserResponse> getUserByPhone(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
    }

    // GET http://localhost:8080/api/v1/users/search?name=john
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam String name) {
        return ResponseEntity.ok(userService.searchUsersByName(name));
    }

    // GET http://localhost:8080/api/v1/users/status/ONLINE
    @GetMapping("/status/{status}")
    public ResponseEntity<List<UserResponse>> getByOnlineStatus(
            @PathVariable User.OnlineStatus status) {
        return ResponseEntity.ok(userService.getUsersByOnlineStatus(status));
    }

    // PUT http://localhost:8080/api/v1/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    // PATCH http://localhost:8080/api/v1/users/{id}/online-status?status=ONLINE
    @PatchMapping("/{id}/online-status")
    public ResponseEntity<UserResponse> updateOnlineStatus(@PathVariable Long id,
                                                           @RequestParam User.OnlineStatus status) {
        return ResponseEntity.ok(userService.updateOnlineStatus(id, status));
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