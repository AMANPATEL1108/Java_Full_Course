package com.example.Databases_System_Design_016.status.controller;

import com.example.Databases_System_Design_016.status.dto.StatusRequest;
import com.example.Databases_System_Design_016.status.dto.StatusResponse;
import com.example.Databases_System_Design_016.status.service.StatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statuses")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    // POST http://localhost:8080/api/v1/statuses
    @PostMapping
    public ResponseEntity<StatusResponse> postStatus(@Valid @RequestBody StatusRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(statusService.postStatus(request));
    }

    // GET http://localhost:8080/api/v1/statuses/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StatusResponse> getStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.getStatusById(id));
    }

    // GET http://localhost:8080/api/v1/statuses/user/{userId}/active
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<StatusResponse>> getActiveStatusesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(statusService.getActiveStatusesByUser(userId));
    }

    // GET http://localhost:8080/api/v1/statuses/user/{userId}/all
    @GetMapping("/user/{userId}/all")
    public ResponseEntity<List<StatusResponse>> getAllStatusesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(statusService.getAllStatusesByUser(userId));
    }

    // POST http://localhost:8080/api/v1/statuses/feed
    // Body: [1, 2, 3]  (list of contact user IDs)
    @PostMapping("/feed")
    public ResponseEntity<List<StatusResponse>> getStatusFeed(@RequestBody List<Long> contactUserIds) {
        return ResponseEntity.ok(statusService.getStatusFeed(contactUserIds));
    }

    // PATCH http://localhost:8080/api/v1/statuses/{id}/view
    @PatchMapping("/{id}/view")
    public ResponseEntity<StatusResponse> viewStatus(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.viewStatus(id));
    }

    // DELETE http://localhost:8080/api/v1/statuses/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }

    // POST http://localhost:8080/api/v1/statuses/expire
    @PostMapping("/expire")
    public ResponseEntity<Void> expireOldStatuses() {
        statusService.expireOldStatuses();
        return ResponseEntity.noContent().build();
    }
}