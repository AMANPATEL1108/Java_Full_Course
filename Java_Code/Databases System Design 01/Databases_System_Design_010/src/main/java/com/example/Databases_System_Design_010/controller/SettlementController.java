package com.example.Databases_System_Design_010.controller;

import com.example.Databases_System_Design_010.dto.request.SettlementRequest;
import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.SettlementResponse;
import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.service.SettlementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/settlements")
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;

    // POST /api/settlements
    @PostMapping
    public ResponseEntity<ApiResponse<SettlementResponse>> settleUp(
            @Valid @RequestBody SettlementRequest request,
            @AuthenticationPrincipal User currentUser) {
        SettlementResponse response = settlementService.settleUp(request, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Settlement recorded successfully", response));
    }

    // GET /api/settlements/{settlementUuid}
    @GetMapping("/{settlementUuid}")
    public ResponseEntity<ApiResponse<SettlementResponse>> getSettlementByUuid(
            @PathVariable UUID settlementUuid) {
        SettlementResponse response = settlementService.getSettlementByUuid(settlementUuid);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/settlements/group/{groupUuid}
    @GetMapping("/group/{groupUuid}")
    public ResponseEntity<ApiResponse<List<SettlementResponse>>> getSettlementsByGroup(
            @PathVariable UUID groupUuid) {
        List<SettlementResponse> settlements = settlementService.getSettlementsByGroup(groupUuid);
        return ResponseEntity.ok(ApiResponse.success(settlements));
    }

    // GET /api/settlements/user/{userUuid}
    @GetMapping("/user/{userUuid}")
    public ResponseEntity<ApiResponse<List<SettlementResponse>>> getSettlementsByUser(
            @PathVariable UUID userUuid) {
        List<SettlementResponse> settlements = settlementService.getSettlementsByUser(userUuid);
        return ResponseEntity.ok(ApiResponse.success(settlements));
    }
}