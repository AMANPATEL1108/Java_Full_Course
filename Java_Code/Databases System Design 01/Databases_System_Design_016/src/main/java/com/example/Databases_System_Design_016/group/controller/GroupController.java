package com.example.Databases_System_Design_016.group.controller;

import com.example.Databases_System_Design_016.group.dto.GroupRequest;
import com.example.Databases_System_Design_016.group.dto.GroupResponse;
import com.example.Databases_System_Design_016.group.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    // POST http://localhost:8080/api/v1/groups
    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@Valid @RequestBody GroupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(request));
    }

    // GET http://localhost:8080/api/v1/groups/{id}
    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    // GET http://localhost:8080/api/v1/groups/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupResponse>> getGroupsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(groupService.getGroupsByUser(userId));
    }

    // GET http://localhost:8080/api/v1/groups/search?name=family
    @GetMapping("/search")
    public ResponseEntity<List<GroupResponse>> searchGroups(@RequestParam String name) {
        return ResponseEntity.ok(groupService.searchGroups(name));
    }

    // GET http://localhost:8080/api/v1/groups/{id}/member-count
    @GetMapping("/{id}/member-count")
    public ResponseEntity<Long> getMemberCount(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getMemberCount(id));
    }

    // PUT http://localhost:8080/api/v1/groups/{id}
    @PutMapping("/{id}")
    public ResponseEntity<GroupResponse> updateGroup(@PathVariable Long id,
                                                     @Valid @RequestBody GroupRequest request) {
        return ResponseEntity.ok(groupService.updateGroup(id, request));
    }

    // POST http://localhost:8080/api/v1/groups/{groupId}/members/{userId}
    @PostMapping("/{groupId}/members/{userId}")
    public ResponseEntity<GroupResponse> addMember(@PathVariable Long groupId,
                                                   @PathVariable Long userId) {
        return ResponseEntity.ok(groupService.addMember(groupId, userId));
    }

    // DELETE http://localhost:8080/api/v1/groups/{groupId}/members/{userId}
    @DeleteMapping("/{groupId}/members/{userId}")
    public ResponseEntity<GroupResponse> removeMember(@PathVariable Long groupId,
                                                      @PathVariable Long userId) {
        return ResponseEntity.ok(groupService.removeMember(groupId, userId));
    }

    // PATCH http://localhost:8080/api/v1/groups/{groupId}/members/{userId}/promote
    @PatchMapping("/{groupId}/members/{userId}/promote")
    public ResponseEntity<GroupResponse> promoteMember(@PathVariable Long groupId,
                                                       @PathVariable Long userId) {
        return ResponseEntity.ok(groupService.promoteMember(groupId, userId));
    }

    // PATCH http://localhost:8080/api/v1/groups/{groupId}/members/{userId}/demote
    @PatchMapping("/{groupId}/members/{userId}/demote")
    public ResponseEntity<GroupResponse> demoteMember(@PathVariable Long groupId,
                                                      @PathVariable Long userId) {
        return ResponseEntity.ok(groupService.demoteMember(groupId, userId));
    }

    // DELETE http://localhost:8080/api/v1/groups/{groupId}/leave/{userId}
    @DeleteMapping("/{groupId}/leave/{userId}")
    public ResponseEntity<Void> leaveGroup(@PathVariable Long groupId,
                                           @PathVariable Long userId) {
        groupService.leaveGroup(groupId, userId);
        return ResponseEntity.noContent().build();
    }

    // DELETE http://localhost:8080/api/v1/groups/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}