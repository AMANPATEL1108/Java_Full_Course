package com.example.Databases_System_Design_010.controller;


import com.example.Databases_System_Design_010.dto.request.SettlementRequest;
import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.SettlementResponse;
import com.example.Databases_System_Design_010.service.SettlementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    // POST /settlements
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<SettlementResponse>> settleUp(@Valid @RequestBody SettlementRequest request) {
        // TODO: implement
        return null;
    }

    // GET /settlements/{id}
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<SettlementResponse>> getSettlementById(@PathVariable Long id) {
        // TODO: implement
        return null;
    }

    // GET /settlements/group/{groupId}
    @GetMapping("/group/{groupId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<SettlementResponse>>> getSettlementsByGroup(@PathVariable Long groupId) {
        // TODO: implement
        return null;
    }

    // GET /settlements/user/{userId}
    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<SettlementResponse>>> getSettlementsByUser(@PathVariable Long userId) {
        // TODO: implement
        return null;
    }
}