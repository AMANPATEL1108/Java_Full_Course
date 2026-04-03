package com.example.Databases_System_Design_016.group.service.impl;

import com.example.Databases_System_Design_016.group.dto.GroupRequest;
import com.example.Databases_System_Design_016.group.dto.GroupResponse;
import com.example.Databases_System_Design_016.group.entity.Group;
import com.example.Databases_System_Design_016.group.entity.GroupMember;
import com.example.Databases_System_Design_016.group.repository.GroupMemberRepository;
import com.example.Databases_System_Design_016.group.repository.GroupRepository;
import com.example.Databases_System_Design_016.group.service.GroupService;
import com.example.Databases_System_Design_016.user.entity.User;
import com.example.Databases_System_Design_016.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    @Override
    public GroupResponse createGroup(GroupRequest request) {
        User creator = findUserOrThrow(request.getCreatedById());

        Group group = Group.builder()
                .name(request.getName())
                .description(request.getDescription())
                .groupIconUrl(request.getGroupIconUrl())
                .createdBy(creator)
                .isActive(true)
                .build();

        Group saved = groupRepository.save(group);

        // Add creator as ADMIN
        GroupMember creatorMember = GroupMember.builder()
                .group(saved)
                .user(creator)
                .role(GroupMember.GroupRole.ADMIN)
                .isMuted(false)
                .build();
        groupMemberRepository.save(creatorMember);

        // Add initial members as MEMBER
        if (request.getMemberIds() != null) {
            for (Long memberId : request.getMemberIds()) {
                if (!memberId.equals(request.getCreatedById())
                        && !groupMemberRepository.existsByGroupIdAndUserId(saved.getId(), memberId)) {
                    User member = findUserOrThrow(memberId);
                    GroupMember gm = GroupMember.builder()
                            .group(saved)
                            .user(member)
                            .role(GroupMember.GroupRole.MEMBER)
                            .isMuted(false)
                            .build();
                    groupMemberRepository.save(gm);
                }
            }
        }

        return GroupResponse.from(groupRepository.findById(saved.getId()).orElse(saved));
    }

    @Override
    @Transactional(readOnly = true)
    public GroupResponse getGroupById(Long id) {
        return GroupResponse.from(findGroupOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupResponse> getGroupsByUser(Long userId) {
        return groupRepository.findGroupsByMemberId(userId).stream()
                .map(GroupResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupResponse> searchGroups(String name) {
        return groupRepository.findByNameContainingIgnoreCaseAndIsActive(name, true).stream()
                .map(GroupResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public GroupResponse updateGroup(Long id, GroupRequest request) {
        Group group = findGroupOrThrow(id);
        if (request.getName() != null) group.setName(request.getName());
        if (request.getDescription() != null) group.setDescription(request.getDescription());
        if (request.getGroupIconUrl() != null) group.setGroupIconUrl(request.getGroupIconUrl());
        return GroupResponse.from(groupRepository.save(group));
    }

    @Override
    public GroupResponse addMember(Long groupId, Long userId) {
        Group group = findGroupOrThrow(groupId);

        if (groupMemberRepository.existsByGroupIdAndUserId(groupId, userId)) {
            throw new RuntimeException("User is already a member of this group");
        }

        User user = findUserOrThrow(userId);
        GroupMember gm = GroupMember.builder()
                .group(group)
                .user(user)
                .role(GroupMember.GroupRole.MEMBER)
                .isMuted(false)
                .build();
        groupMemberRepository.save(gm);

        return GroupResponse.from(groupRepository.findById(groupId).orElse(group));
    }

    @Override
    public GroupResponse removeMember(Long groupId, Long userId) {
        if (!groupMemberRepository.existsByGroupIdAndUserId(groupId, userId)) {
            throw new RuntimeException("User is not a member of this group");
        }
        groupMemberRepository.deleteByGroupIdAndUserId(groupId, userId);
        return GroupResponse.from(findGroupOrThrow(groupId));
    }

    @Override
    public GroupResponse promoteMember(Long groupId, Long userId) {
        GroupMember gm = groupMemberRepository.findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Member not found in group"));
        gm.setRole(GroupMember.GroupRole.ADMIN);
        groupMemberRepository.save(gm);
        return GroupResponse.from(findGroupOrThrow(groupId));
    }

    @Override
    public GroupResponse demoteMember(Long groupId, Long userId) {
        GroupMember gm = groupMemberRepository.findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Member not found in group"));
        gm.setRole(GroupMember.GroupRole.MEMBER);
        groupMemberRepository.save(gm);
        return GroupResponse.from(findGroupOrThrow(groupId));
    }

    @Override
    public void leaveGroup(Long groupId, Long userId) {
        if (!groupMemberRepository.existsByGroupIdAndUserId(groupId, userId)) {
            throw new RuntimeException("User is not a member of this group");
        }
        groupMemberRepository.deleteByGroupIdAndUserId(groupId, userId);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = findGroupOrThrow(id);
        group.setIsActive(false);
        groupRepository.save(group);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getMemberCount(Long groupId) {
        return groupMemberRepository.countByGroupId(groupId);
    }

    private Group findGroupOrThrow(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}