package com.example.Databases_System_Design_010.controller;


import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.BalanceSummaryResponse;
import com.example.Databases_System_Design_010.dto.response.UserBalanceResponse;
import com.example.Databases_System_Design_010.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    // GET /balances/group?userId=1&groupId=1
    @GetMapping("/group")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<BalanceSummaryResponse>> getGroupBalance(
            @RequestParam Long userId,
            @RequestParam Long groupId) {
        // TODO: implement
        return null;
    }

    // GET /balances/overall/{userId}
    @GetMapping("/overall/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<UserBalanceResponse>>> getOverallBalance(@PathVariable Long userId) {
        // TODO: implement
        return null;
    }
}
