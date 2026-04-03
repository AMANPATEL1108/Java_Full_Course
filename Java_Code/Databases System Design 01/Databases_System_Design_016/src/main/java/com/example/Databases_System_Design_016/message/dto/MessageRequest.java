package com.example.Databases_System_Design_016.message.dto;

import com.example.Databases_System_Design_016.message.entity.Message;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {

    @NotNull(message = "Chat ID is required")
    private Long chatId;

    @NotNull(message = "Sender ID is required")
    private Long senderId;

    private String content;

    private Message.MessageType messageType = Message.MessageType.TEXT;

    private String mediaUrl;

    private String mediaFileName;

    private Long mediaSizeBytes;

    private Long replyToMessageId;
}