package com.example.Databases_System_Design_016.chat.dto;

import com.example.Databases_System_Design_016.chat.entity.Chat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatRequest {

    @NotNull(message = "Chat type is required")
    private Chat.ChatType chatType;

    private Long senderId;

    private Long receiverId;

    private Long groupId;
}