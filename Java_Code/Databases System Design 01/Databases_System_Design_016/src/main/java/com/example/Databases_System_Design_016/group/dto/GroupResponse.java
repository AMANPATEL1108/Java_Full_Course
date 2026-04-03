package com.example.Databases_System_Design_016.group.dto;

import com.example.Databases_System_Design_016.group.entity.Group;
import com.example.Databases_System_Design_016.group.entity.GroupMember;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GroupResponse {

    private Long id;
    private String name;
    private String description;
    private String groupIconUrl;
    private Long createdById;
    private String createdByDisplayName;
    private Long memberCount;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private List<GroupMemberInfo> members;

    @Data
    @Builder
    public static class GroupMemberInfo {
        private Long userId;
        private String displayName;
        private String profilePictureUrl;
        private GroupMember.GroupRole role;
        private LocalDateTime joinedAt;

        public static GroupMemberInfo from(GroupMember gm) {
            return GroupMemberInfo.builder()
                    .userId(gm.getUser().getId())
                    .displayName(gm.getUser().getDisplayName())
                    .profilePictureUrl(gm.getUser().getProfilePictureUrl())
                    .role(gm.getRole())
                    .joinedAt(gm.getJoinedAt())
                    .build();
        }
    }

    public static GroupResponse from(Group g) {
        GroupResponse.GroupResponseBuilder builder = GroupResponse.builder()
                .id(g.getId())
                .name(g.getName())
                .description(g.getDescription())
                .groupIconUrl(g.getGroupIconUrl())
                .createdById(g.getCreatedBy().getId())
                .createdByDisplayName(g.getCreatedBy().getDisplayName())
                .isActive(g.getIsActive())
                .createdAt(g.getCreatedAt());

        if (g.getMembers() != null) {
            builder.memberCount((long) g.getMembers().size())
                    .members(g.getMembers().stream()
                            .map(GroupMemberInfo::from)
                            .toList());
        }
        return builder.build();
    }
}