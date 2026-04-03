package com.example.Databases_System_Design_016.message.service.impl;

import com.example.Databases_System_Design_016.chat.entity.Chat;
import com.example.Databases_System_Design_016.chat.repository.ChatRepository;
import com.example.Databases_System_Design_016.message.dto.MessageRequest;
import com.example.Databases_System_Design_016.message.dto.MessageResponse;
import com.example.Databases_System_Design_016.message.entity.Message;
import com.example.Databases_System_Design_016.message.repository.MessageRepository;
import com.example.Databases_System_Design_016.message.service.MessageService;
import com.example.Databases_System_Design_016.user.entity.User;
import com.example.Databases_System_Design_016.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public MessageResponse sendMessage(MessageRequest request) {
        Chat chat = chatRepository.findById(request.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat not found with id: " + request.getChatId()));

        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getSenderId()));

        Message message = Message.builder()
                .chat(chat)
                .sender(sender)
                .content(request.getContent())
                .messageType(request.getMessageType() != null
                        ? request.getMessageType()
                        : Message.MessageType.TEXT)
                .status(Message.MessageStatus.SENT)
                .mediaUrl(request.getMediaUrl())
                .mediaFileName(request.getMediaFileName())
                .mediaSizeBytes(request.getMediaSizeBytes())
                .isDeleted(false)
                .isEdited(false)
                .build();

        if (request.getReplyToMessageId() != null) {
            Message replyTo = messageRepository.findById(request.getReplyToMessageId())
                    .orElseThrow(() -> new RuntimeException("Reply-to message not found"));
            message.setReplyToMessage(replyTo);
        }

        Message saved = messageRepository.save(message);

        // Update chat's last message snapshot
        chat.setLastMessage(request.getContent() != null
                ? request.getContent()
                : "[" + message.getMessageType().name().toLowerCase() + "]");
        chat.setLastMessageAt(LocalDateTime.now());
        chat.setUnreadCount(chat.getUnreadCount() + 1);
        chatRepository.save(chat);

        return MessageResponse.from(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MessageResponse getMessageById(Long id) {
        return MessageResponse.from(findMessageOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageResponse> getMessagesByChat(Long chatId) {
        return messageRepository
                .findByChatIdAndIsDeletedOrderBySentAtAsc(chatId, false)
                .stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageResponse> getUnreadMessages(Long chatId, Long userId) {
        return messageRepository.findUnreadMessages(chatId, userId)
                .stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageResponse> searchMessages(Long chatId, String keyword) {
        return messageRepository.searchMessages(chatId, keyword)
                .stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse updateMessageStatus(Long messageId, Message.MessageStatus status) {
        Message message = findMessageOrThrow(messageId);
        message.setStatus(status);

        if (status == Message.MessageStatus.DELIVERED) {
            message.setDeliveredAt(LocalDateTime.now());
        } else if (status == Message.MessageStatus.READ) {
            message.setReadAt(LocalDateTime.now());
        }

        return MessageResponse.from(messageRepository.save(message));
    }

    @Override
    public MessageResponse editMessage(Long messageId, String newContent) {
        Message message = findMessageOrThrow(messageId);

        if (message.getIsDeleted()) {
            throw new RuntimeException("Cannot edit a deleted message");
        }

        message.setContent(newContent);
        message.setIsEdited(true);
        message.setEditedAt(LocalDateTime.now());

        return MessageResponse.from(messageRepository.save(message));
    }

    @Override
    public void deleteMessage(Long messageId) {
        Message message = findMessageOrThrow(messageId);
        message.setIsDeleted(true);
        message.setContent(null);
        message.setMediaUrl(null);
        messageRepository.save(message);
    }

    @Override
    public void markAllMessagesAsRead(Long chatId, Long userId) {
        List<Message> unread = messageRepository.findUnreadMessages(chatId, userId);
        unread.forEach(m -> {
            m.setStatus(Message.MessageStatus.READ);
            m.setReadAt(LocalDateTime.now());
        });
        messageRepository.saveAll(unread);

        // Reset unread count on chat
        chatRepository.findById(chatId).ifPresent(chat -> {
            chat.setUnreadCount(0L);
            chatRepository.save(chat);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Long getUnreadCount(Long chatId) {
        return messageRepository.countByChatIdAndIsDeletedAndStatusNot(
                chatId, false, Message.MessageStatus.READ);
    }

    private Message findMessageOrThrow(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
    }
}