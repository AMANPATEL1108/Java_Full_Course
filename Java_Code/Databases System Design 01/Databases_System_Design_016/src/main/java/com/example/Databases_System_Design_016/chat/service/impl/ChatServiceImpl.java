package com.example.Databases_System_Design_016.chat.service.impl;

import com.example.Databases_System_Design_016.chat.dto.ChatRequest;
import com.example.Databases_System_Design_016.chat.dto.ChatResponse;
import com.example.Databases_System_Design_016.chat.entity.Chat;
import com.example.Databases_System_Design_016.chat.repository.ChatRepository;
import com.example.Databases_System_Design_016.chat.service.ChatService;
import com.example.Databases_System_Design_016.group.entity.Group;
import com.example.Databases_System_Design_016.group.repository.GroupRepository;
import com.example.Databases_System_Design_016.user.entity.User;
import com.example.Databases_System_Design_016.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public ChatResponse createPrivateChat(Long senderId, Long receiverId) {
        if (senderId.equals(receiverId)) {
            throw new RuntimeException("Cannot create chat with yourself");
        }
        return chatRepository.findPrivateChat(senderId, receiverId)
                .map(ChatResponse::from)
                .orElseGet(() -> {
                    User sender   = findUserOrThrow(senderId);
                    User receiver = findUserOrThrow(receiverId);
                    Chat chat = Chat.builder()
                            .chatType(Chat.ChatType.PRIVATE)
                            .sender(sender)
                            .receiver(receiver)
                            .unreadCount(0L)
                            .isActive(true)
                            .build();
                    return ChatResponse.from(chatRepository.save(chat));
                });
    }

    @Override
    public ChatResponse createGroupChat(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: " + groupId));
        return chatRepository.findByGroupIdAndIsActive(groupId, true).stream()
                .findFirst()
                .map(ChatResponse::from)
                .orElseGet(() -> {
                    Chat chat = Chat.builder()
                            .chatType(Chat.ChatType.GROUP)
                            .group(group)
                            .unreadCount(0L)
                            .isActive(true)
                            .build();
                    return ChatResponse.from(chatRepository.save(chat));
                });
    }

    @Override
    @Transactional(readOnly = true)
    public ChatResponse getChatById(Long id) {
        return ChatResponse.from(findChatOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatResponse> getAllChatsForUser(Long userId) {
        List<Chat> privateChats = chatRepository.findAllPrivateChatsByUser(userId);
        List<Chat> groupChats   = chatRepository.findAllGroupChatsByUser(userId);
        List<Chat> all = new ArrayList<>();
        all.addAll(privateChats);
        all.addAll(groupChats);
        all.sort((a, b) -> {
            if (a.getLastMessageAt() == null) return 1;
            if (b.getLastMessageAt() == null) return -1;
            return b.getLastMessageAt().compareTo(a.getLastMessageAt());
        });
        return all.stream().map(ChatResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatResponse> getPrivateChatsForUser(Long userId) {
        return chatRepository.findAllPrivateChatsByUser(userId).stream()
                .map(ChatResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatResponse> getGroupChatsForUser(Long userId) {
        return chatRepository.findAllGroupChatsByUser(userId).stream()
                .map(ChatResponse::from).collect(Collectors.toList());
    }

    @Override
    public ChatResponse markChatAsRead(Long chatId) {
        Chat chat = findChatOrThrow(chatId);
        chat.setUnreadCount(0L);
        return ChatResponse.from(chatRepository.save(chat));
    }

    @Override
    public void deleteChat(Long id) {
        Chat chat = findChatOrThrow(id);
        chat.setIsActive(false);
        chatRepository.save(chat);
    }

    private Chat findChatOrThrow(Long id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found with id: " + id));
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}