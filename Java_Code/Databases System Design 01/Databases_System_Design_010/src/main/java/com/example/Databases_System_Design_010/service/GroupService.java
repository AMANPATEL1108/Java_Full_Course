package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.request.AddMemberRequest;
import com.example.Databases_System_Design_010.dto.request.GroupRequest;
import com.example.Databases_System_Design_010.dto.response.GroupResponse;

import java.util.List;

public interface GroupService {
    GroupResponse createGroup(GroupRequest request, Long createdByUserId);
    GroupResponse getGroupById(Long groupId);
    List<GroupResponse> getAllGroupsForUser(Long userId);
    GroupResponse addMember(Long groupId, AddMemberRequest request);
    void removeMember(Long groupId, Long userId);
    void deleteGroup(Long groupId, Long requestedByUserId);
}