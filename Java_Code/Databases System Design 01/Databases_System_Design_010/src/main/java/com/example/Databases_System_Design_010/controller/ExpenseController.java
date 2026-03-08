package com.example.Databases_System_Design_010.controller;


import com.example.Databases_System_Design_010.dto.request.ExpenseRequest;
import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.ExpenseResponse;
import com.example.Databases_System_Design_010.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // POST /expenses
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ExpenseResponse>> addExpense(@Valid @RequestBody ExpenseRequest request) {
        // TODO: implement
        return null;
    }

    // GET /expenses/{id}
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ExpenseResponse>> getExpenseById(@PathVariable Long id) {
        // TODO: implement
        return null;
    }

    // GET /expenses/group/{groupId}
    @GetMapping("/group/{groupId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getExpensesByGroup(@PathVariable Long groupId) {
        // TODO: implement
        return null;
    }

    // GET /expenses/user/{userId}
    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getExpensesByUser(@PathVariable Long userId) {
        // TODO: implement
        return null;
    }

    // DELETE /expenses/{id}?requestedByUserId=1
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(
            @PathVariable Long id,
            @RequestParam Long requestedByUserId) {
        // TODO: implement
        return null;
    }
}