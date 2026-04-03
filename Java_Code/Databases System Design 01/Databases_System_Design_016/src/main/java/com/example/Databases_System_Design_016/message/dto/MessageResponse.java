package com.example.Databases_System_Design_016.message.dto;

import com.example.Databases_System_Design_016.message.entity.Message;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageResponse {

    private Long id;
    private Long chatId;
    private Long senderId;
    private String senderDisplayName;
    private String senderProfilePictureUrl;
    private String content;
    private Message.MessageType messageType;
    private Message.MessageStatus status;
    private String mediaUrl;
    private String mediaFileName;
    private Long mediaSizeBytes;
    private Long replyToMessageId;
    private String replyToContent;
    private Boolean isDeleted;
    private Boolean isEdited;
    private LocalDateTime sentAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime readAt;

    public static MessageResponse from(Message m) {
        return MessageResponse.builder()
                .id(m.getId())
                .chatId(m.getChat().getId())
                .senderId(m.getSender().getId())
                .senderDisplayName(m.getSender().getDisplayName())
                .senderProfilePictureUrl(m.getSender().getProfilePictureUrl())
                .content(m.getContent())
                .messageType(m.getMessageType())
                .status(m.getStatus())
                .mediaUrl(m.getMediaUrl())
                .mediaFileName(m.getMediaFileName())
                .mediaSizeBytes(m.getMediaSizeBytes())
                .replyToMessageId(m.getReplyToMessage() != null ? m.getReplyToMessage().getId() : null)
                .replyToContent(m.getReplyToMessage() != null ? m.getReplyToMessage().getContent() : null)
                .isDeleted(m.getIsDeleted())
                .isEdited(m.getIsEdited())
                .sentAt(m.getSentAt())
                .deliveredAt(m.getDeliveredAt())
                .readAt(m.getReadAt())
                .build();
    }
}