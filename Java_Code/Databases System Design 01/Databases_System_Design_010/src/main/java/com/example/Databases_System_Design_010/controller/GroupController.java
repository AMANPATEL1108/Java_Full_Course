package com.example.Databases_System_Design_010.controller;

import com.example.Databases_System_Design_010.dto.request.AddMemberRequest;
import com.example.Databases_System_Design_010.dto.request.GroupRequest;
import com.example.Databases_System_Design_010.dto.response.ApiResponse;
import com.example.Databases_System_Design_010.dto.response.GroupResponse;
import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    // POST /api/groups
    @PostMapping
    public ResponseEntity<ApiResponse<GroupResponse>> createGroup(
            @Valid @RequestBody GroupRequest request,
            @AuthenticationPrincipal User currentUser) {
        GroupResponse response = groupService.createGroup(request, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Group created successfully", response));
    }

    // GET /api/groups/{groupUuid}
    @GetMapping("/{groupUuid}")
    public ResponseEntity<ApiResponse<GroupResponse>> getGroupByUuid(@PathVariable UUID groupUuid) {
        GroupResponse response = groupService.getGroupByUuid(groupUuid);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/groups/my  — groups where current user is a member
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<GroupResponse>>> getMyGroups(
            @AuthenticationPrincipal User currentUser) {
        List<GroupResponse> groups = groupService.getAllGroupsForUser(currentUser);
        return ResponseEntity.ok(ApiResponse.success(groups));
    }

    // POST /api/groups/{groupUuid}/members
    @PostMapping("/{groupUuid}/members")
    public ResponseEntity<ApiResponse<GroupResponse>> addMember(
            @PathVariable UUID groupUuid,
            @Valid @RequestBody AddMemberRequest request,
            @AuthenticationPrincipal User currentUser) {
        GroupResponse response = groupService.addMember(groupUuid, request, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Member added successfully", response));
    }

    // DELETE /api/groups/{groupUuid}/members/{userUuid}
    @DeleteMapping("/{groupUuid}/members/{userUuid}")
    public ResponseEntity<ApiResponse<Void>> removeMember(
            @PathVariable UUID groupUuid,
            @PathVariable UUID userUuid,
            @AuthenticationPrincipal User currentUser) {
        groupService.removeMember(groupUuid, userUuid, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Member removed successfully", null));
    }

    // DELETE /api/groups/{groupUuid}
    @DeleteMapping("/{groupUuid}")
    public ResponseEntity<ApiResponse<Void>> deleteGroup(
            @PathVariable UUID groupUuid,
            @AuthenticationPrincipal User currentUser) {
        groupService.deleteGroup(groupUuid, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Group deleted successfully", null));
    }
}