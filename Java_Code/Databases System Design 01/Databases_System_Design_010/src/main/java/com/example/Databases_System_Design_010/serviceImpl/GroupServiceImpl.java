package com.example.Databases_System_Design_010.serviceImpl;


import com.example.Databases_System_Design_010.dto.request.AddMemberRequest;
import com.example.Databases_System_Design_010.dto.request.GroupRequest;
import com.example.Databases_System_Design_010.dto.response.GroupResponse;
import com.example.Databases_System_Design_010.repository.GroupMemberRepository;
import com.example.Databases_System_Design_010.repository.GroupRepository;
import com.example.Databases_System_Design_010.repository.UserRepository;
import com.example.Databases_System_Design_010.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    public GroupServiceImpl(GroupRepository groupRepository,
                            GroupMemberRepository groupMemberRepository,
                            UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GroupResponse createGroup(GroupRequest request, Long createdByUserId) {
        // TODO: implement
        return null;
    }

    @Override
    public GroupResponse getGroupById(Long groupId) {
        // TODO: implement
        return null;
    }

    @Override
    public List<GroupResponse> getAllGroupsForUser(Long userId) {
        // TODO: implement
        return null;
    }

    @Override
    public GroupResponse addMember(Long groupId, AddMemberRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public void removeMember(Long groupId, Long userId) {
        // TODO: implement
    }

    @Override
    public void deleteGroup(Long groupId, Long requestedByUserId) {
        // TODO: implement
    }
}
