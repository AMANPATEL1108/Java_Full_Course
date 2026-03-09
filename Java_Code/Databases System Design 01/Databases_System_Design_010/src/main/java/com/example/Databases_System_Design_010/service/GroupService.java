package com.example.Databases_System_Design_010.service;

import com.example.Databases_System_Design_010.dto.request.AddMemberRequest;
import com.example.Databases_System_Design_010.dto.request.GroupRequest;
import com.example.Databases_System_Design_010.dto.response.GroupResponse;
import com.example.Databases_System_Design_010.entity.User;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    GroupResponse createGroup(GroupRequest request, User currentUser);
    GroupResponse getGroupByUuid(UUID groupUuid);
    List<GroupResponse> getAllGroupsForUser(User currentUser);
    GroupResponse addMember(UUID groupUuid, AddMemberRequest request, User currentUser);
    void removeMember(UUID groupUuid, UUID userUuid, User currentUser);
    void deleteGroup(UUID groupUuid, User currentUser);
}