package com.example.Databases_System_Design_014.api.subscription.controller;

import com.example.Databases_System_Design_014.api.subscription.dto.SubscriptionRequest;
import com.example.Databases_System_Design_014.api.subscription.dto.SubscriptionResponse;
import com.example.Databases_System_Design_014.api.subscription.entity.Subscription;
import com.example.Databases_System_Design_014.api.subscription.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> create(@Valid @RequestBody SubscriptionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.createSubscription(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubscriptionResponse>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUser(userId));
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<SubscriptionResponse> getActiveByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getActiveSubscription(userId));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> getAll() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SubscriptionResponse>> getByStatus(
            @PathVariable Subscription.SubscriptionStatus status) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByStatus(status));
    }

    @GetMapping("/expired")
    public ResponseEntity<List<SubscriptionResponse>> getExpired() {
        return ResponseEntity.ok(subscriptionService.getExpiredSubscriptions());
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<SubscriptionResponse> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.cancelSubscription(id));
    }

    @PatchMapping("/{id}/pause")
    public ResponseEntity<SubscriptionResponse> pause(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.pauseSubscription(id));
    }

    @PatchMapping("/{id}/resume")
    public ResponseEntity<SubscriptionResponse> resume(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.resumeSubscription(id));
    }
}