package com.example.Databases_System_Design_010.controller;

import com.example.Databases_System_Design_010.dto.request.ExpenseRequest;
import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.ExpenseResponse;
import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    // POST /api/expenses
    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponse>> addExpense(
            @Valid @RequestBody ExpenseRequest request,
            @AuthenticationPrincipal User currentUser) {
        ExpenseResponse response = expenseService.addExpense(request, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Expense added successfully", response));
    }

    // GET /api/expenses/{expenseUuid}
    @GetMapping("/{expenseUuid}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> getExpenseByUuid(@PathVariable UUID expenseUuid) {
        ExpenseResponse response = expenseService.getExpenseByUuid(expenseUuid);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/expenses/group/{groupUuid}
    @GetMapping("/group/{groupUuid}")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getExpensesByGroup(@PathVariable UUID groupUuid) {
        List<ExpenseResponse> expenses = expenseService.getExpensesByGroup(groupUuid);
        return ResponseEntity.ok(ApiResponse.success(expenses));
    }

    // GET /api/expenses/user/{userUuid}
    @GetMapping("/user/{userUuid}")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getExpensesByUser(@PathVariable UUID userUuid) {
        List<ExpenseResponse> expenses = expenseService.getExpensesByUser(userUuid);
        return ResponseEntity.ok(ApiResponse.success(expenses));
    }

    // DELETE /api/expenses/{expenseUuid}
    @DeleteMapping("/{expenseUuid}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(
            @PathVariable UUID expenseUuid,
            @AuthenticationPrincipal User currentUser) {
        expenseService.deleteExpense(expenseUuid, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Expense deleted successfully", null));
    }
}