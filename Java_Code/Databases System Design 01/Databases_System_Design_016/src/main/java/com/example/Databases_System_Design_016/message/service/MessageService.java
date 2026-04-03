package com.example.Databases_System_Design_016.message.service;

import com.example.Databases_System_Design_016.message.dto.MessageRequest;
import com.example.Databases_System_Design_016.message.dto.MessageResponse;
import com.example.Databases_System_Design_016.message.entity.Message;

import java.util.List;

public interface MessageService {

    MessageResponse sendMessage(MessageRequest request);

    MessageResponse getMessageById(Long id);

    List<MessageResponse> getMessagesByChat(Long chatId);

    List<MessageResponse> getUnreadMessages(Long chatId, Long userId);

    List<MessageResponse> searchMessages(Long chatId, String keyword);

    MessageResponse updateMessageStatus(Long messageId, Message.MessageStatus status);

    MessageResponse editMessage(Long messageId, String newContent);

    void deleteMessage(Long messageId);

    void markAllMessagesAsRead(Long chatId, Long userId);

    Long getUnreadCount(Long chatId);
}