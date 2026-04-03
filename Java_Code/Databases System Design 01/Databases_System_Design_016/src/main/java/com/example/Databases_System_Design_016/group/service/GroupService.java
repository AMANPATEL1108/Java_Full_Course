package com.example.Databases_System_Design_016.group.service;

import com.example.Databases_System_Design_016.group.dto.GroupRequest;
import com.example.Databases_System_Design_016.group.dto.GroupResponse;
import com.example.Databases_System_Design_016.group.entity.GroupMember;

import java.util.List;

public interface GroupService {

    GroupResponse createGroup(GroupRequest request);

    GroupResponse getGroupById(Long id);

    List<GroupResponse> getGroupsByUser(Long userId);

    List<GroupResponse> searchGroups(String name);

    GroupResponse updateGroup(Long id, GroupRequest request);

    GroupResponse addMember(Long groupId, Long userId);

    GroupResponse removeMember(Long groupId, Long userId);

    GroupResponse promoteMember(Long groupId, Long userId);

    GroupResponse demoteMember(Long groupId, Long userId);

    void leaveGroup(Long groupId, Long userId);

    void deleteGroup(Long id);

    Long getMemberCount(Long groupId);
}