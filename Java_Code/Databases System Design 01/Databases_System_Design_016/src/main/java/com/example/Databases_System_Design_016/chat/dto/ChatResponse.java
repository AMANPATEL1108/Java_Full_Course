package com.example.Databases_System_Design_016.chat.dto;

import com.example.Databases_System_Design_016.chat.entity.Chat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatResponse {

    private Long id;
    private Chat.ChatType chatType;
    private Long senderId;
    private String senderDisplayName;
    private Long receiverId;
    private String receiverDisplayName;
    private String receiverProfilePictureUrl;
    private Long groupId;
    private String groupName;
    private String groupIconUrl;
    private String lastMessage;
    private LocalDateTime lastMessageAt;
    private Long unreadCount;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public static ChatResponse from(Chat c) {
        ChatResponse.ChatResponseBuilder builder = ChatResponse.builder()
                .id(c.getId())
                .chatType(c.getChatType())
                .lastMessage(c.getLastMessage())
                .lastMessageAt(c.getLastMessageAt())
                .unreadCount(c.getUnreadCount())
                .isActive(c.getIsActive())
                .createdAt(c.getCreatedAt());

        if (c.getSender() != null) {
            builder.senderId(c.getSender().getId())
                    .senderDisplayName(c.getSender().getDisplayName());
        }
        if (c.getReceiver() != null) {
            builder.receiverId(c.getReceiver().getId())
                    .receiverDisplayName(c.getReceiver().getDisplayName())
                    .receiverProfilePictureUrl(c.getReceiver().getProfilePictureUrl());
        }
        if (c.getGroup() != null) {
            builder.groupId(c.getGroup().getId())
                    .groupName(c.getGroup().getName())
                    .groupIconUrl(c.getGroup().getGroupIconUrl());
        }
        return builder.build();
    }
}