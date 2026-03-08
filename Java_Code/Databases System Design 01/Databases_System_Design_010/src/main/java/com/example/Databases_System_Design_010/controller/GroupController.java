package com.example.Databases_System_Design_010.controller;


import com.example.Databases_System_Design_010.dto.request.AddMemberRequest;
import com.example.Databases_System_Design_010.dto.request.GroupRequest;
import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.GroupResponse;
import com.example.Databases_System_Design_010.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // POST /groups?userId=1
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<GroupResponse>> createGroup(
            @Valid @RequestBody GroupRequest request,
            @RequestParam Long userId) {
        // TODO: implement
        return null;
    }

    // GET /groups/{id}
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<GroupResponse>> getGroupById(@PathVariable Long id) {
        // TODO: implement
        return null;
    }

    // GET /groups/user/{userId}
    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<GroupResponse>>> getAllGroupsForUser(@PathVariable Long userId) {
        // TODO: implement
        return null;
    }

    // POST /groups/{groupId}/members
    @PostMapping("/{groupId}/members")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<GroupResponse>> addMember(
            @PathVariable Long groupId,
            @Valid @RequestBody AddMemberRequest request) {
        // TODO: implement
        return null;
    }

    // DELETE /groups/{groupId}/members/{userId}
    @DeleteMapping("/{groupId}/members/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Void>> removeMember(
            @PathVariable Long groupId,
            @PathVariable Long userId) {
        // TODO: implement
        return null;
    }

    // DELETE /groups/{groupId}?requestedByUserId=1
    @DeleteMapping("/{groupId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Void>> deleteGroup(
            @PathVariable Long groupId,
            @RequestParam Long requestedByUserId) {
        // TODO: implement
        return null;
    }
}
