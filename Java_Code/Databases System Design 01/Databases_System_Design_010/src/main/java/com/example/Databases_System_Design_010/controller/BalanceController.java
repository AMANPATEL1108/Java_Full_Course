package com.example.Databases_System_Design_010.controller;

import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.BalanceSummaryResponse;
import com.example.Databases_System_Design_010.dto.response.UserBalanceResponse;
import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/balances")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    // GET /api/balances/group/{groupUuid}  — balance summary for a group
    @GetMapping("/group/{groupUuid}")
    public ResponseEntity<ApiResponse<BalanceSummaryResponse>> getGroupBalance(
            @PathVariable UUID groupUuid,
            @AuthenticationPrincipal User currentUser) {
        BalanceSummaryResponse response = balanceService.getGroupBalanceSummary(groupUuid, currentUser);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/balances/overall  — overall balance across all groups for current user
    @GetMapping("/overall")
    public ResponseEntity<ApiResponse<List<UserBalanceResponse>>> getOverallBalance(
            @AuthenticationPrincipal User currentUser) {
        List<UserBalanceResponse> balances = balanceService.getOverallBalances(currentUser);
        return ResponseEntity.ok(ApiResponse.success(balances));
    }
}