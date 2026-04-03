package com.example.Databases_System_Design_016.chat.service;

import com.example.Databases_System_Design_016.chat.dto.ChatRequest;
import com.example.Databases_System_Design_016.chat.dto.ChatResponse;

import java.util.List;

public interface ChatService {

    ChatResponse createPrivateChat(Long senderId, Long receiverId);

    ChatResponse createGroupChat(Long groupId);

    ChatResponse getChatById(Long id);

    List<ChatResponse> getAllChatsForUser(Long userId);

    List<ChatResponse> getPrivateChatsForUser(Long userId);

    List<ChatResponse> getGroupChatsForUser(Long userId);

    ChatResponse markChatAsRead(Long chatId);

    void deleteChat(Long id);
}