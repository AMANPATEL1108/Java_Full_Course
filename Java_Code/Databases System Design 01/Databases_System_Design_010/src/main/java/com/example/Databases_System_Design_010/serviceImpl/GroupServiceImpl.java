package com.example.Databases_System_Design_010.serviceImpl;

import com.example.Databases_System_Design_010.dto.request.AddMemberRequest;
import com.example.Databases_System_Design_010.dto.request.GroupRequest;
import com.example.Databases_System_Design_010.dto.response.GroupResponse;
import com.example.Databases_System_Design_010.entity.Group;
import com.example.Databases_System_Design_010.entity.GroupMember;
import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_010.exception.UnauthorizedAccessException;
import com.example.Databases_System_Design_010.repository.GroupMemberRepository;
import com.example.Databases_System_Design_010.repository.GroupRepository;
import com.example.Databases_System_Design_010.repository.UserRepository;
import com.example.Databases_System_Design_010.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public GroupResponse createGroup(GroupRequest request, User currentUser) {
        Group group = Group.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdBy(currentUser)
                .build();

        group = groupRepository.save(group);

        // Auto-add creator as first member
        GroupMember member = GroupMember.builder()
                .group(group)
                .user(currentUser)
                .build();
        groupMemberRepository.save(member);

        return mapToResponse(group);
    }

    @Override
    public GroupResponse getGroupByUuid(UUID groupUuid) {
        Group group = groupRepository.findByUuid(groupUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + groupUuid));
        return mapToResponse(group);
    }

    @Override
    public List<GroupResponse> getAllGroupsForUser(User currentUser) {
        List<GroupMember> memberships = groupMemberRepository.findByUser(currentUser);
        return memberships.stream()
                .map(m -> mapToResponse(m.getGroup()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GroupResponse addMember(UUID groupUuid, AddMemberRequest request, User currentUser) {
        Group group = groupRepository.findByUuid(groupUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + groupUuid));

        // Only creator can add members
        if (!group.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("Only the group creator can add members");
        }

        User userToAdd = userRepository.findByUuid(request.getUserUuid())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with UUID: " + request.getUserUuid()));

        if (groupMemberRepository.existsByGroupAndUser(group, userToAdd)) {
            throw new IllegalArgumentException("User is already a member of this group");
        }

        GroupMember member = GroupMember.builder()
                .group(group)
                .user(userToAdd)
                .build();
        groupMemberRepository.save(member);

        return mapToResponse(group);
    }

    @Override
    @Transactional
    public void removeMember(UUID groupUuid, UUID userUuid, User currentUser) {
        Group group = groupRepository.findByUuid(groupUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + groupUuid));

        if (!group.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("Only the group creator can remove members");
        }

        User userToRemove = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with UUID: " + userUuid));

        // Cannot remove creator
        if (group.getCreatedBy().getId().equals(userToRemove.getId())) {
            throw new IllegalArgumentException("Cannot remove the group creator");
        }

        GroupMember member = groupMemberRepository.findByGroupAndUser(group, userToRemove)
                .orElseThrow(() -> new ResourceNotFoundException("User is not a member of this group"));

        groupMemberRepository.delete(member);
    }

    @Override
    @Transactional
    public void deleteGroup(UUID groupUuid, User currentUser) {
        Group group = groupRepository.findByUuid(groupUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + groupUuid));

        if (!group.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("Only the group creator can delete the group");
        }

        groupRepository.delete(group);
    }

    private GroupResponse mapToResponse(Group group) {
        List<GroupMember> members = groupMemberRepository.findByGroup(group);

        List<GroupResponse.MemberDetail> memberDetails = members.stream()
                .map(m -> GroupResponse.MemberDetail.builder()
                        .userUuid(m.getUser().getUuid())
                        .name(m.getUser().getName())
                        .email(m.getUser().getEmail())
                        .build())
                .collect(Collectors.toList());

        return GroupResponse.builder()
                .uuid(group.getUuid())
                .name(group.getName())
                .description(group.getDescription())
                .createdByUuid(group.getCreatedBy().getUuid())
                .createdByName(group.getCreatedBy().getName())
                .createdByEmail(group.getCreatedBy().getEmail())
                .createdAt(group.getCreatedAt())
                .members(memberDetails)
                .totalMembers(memberDetails.size())
                .build();
    }
}