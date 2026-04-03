package com.example.Databases_System_Design_016.message.controller;

import com.example.Databases_System_Design_016.message.dto.MessageRequest;
import com.example.Databases_System_Design_016.message.dto.MessageResponse;
import com.example.Databases_System_Design_016.message.entity.Message;
import com.example.Databases_System_Design_016.message.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // POST http://localhost:8080/api/v1/messages
    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@Valid @RequestBody MessageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessage(request));
    }

    // GET http://localhost:8080/api/v1/messages/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    // GET http://localhost:8080/api/v1/messages/chat/{chatId}
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageResponse>> getMessagesByChat(@PathVariable Long chatId) {
        return ResponseEntity.ok(messageService.getMessagesByChat(chatId));
    }

    // GET http://localhost:8080/api/v1/messages/chat/{chatId}/unread?userId=2
    @GetMapping("/chat/{chatId}/unread")
    public ResponseEntity<List<MessageResponse>> getUnreadMessages(@PathVariable Long chatId,
                                                                   @RequestParam Long userId) {
        return ResponseEntity.ok(messageService.getUnreadMessages(chatId, userId));
    }

    // GET http://localhost:8080/api/v1/messages/chat/{chatId}/search?keyword=hello
    @GetMapping("/chat/{chatId}/search")
    public ResponseEntity<List<MessageResponse>> searchMessages(@PathVariable Long chatId,
                                                                @RequestParam String keyword) {
        return ResponseEntity.ok(messageService.searchMessages(chatId, keyword));
    }

    // GET http://localhost:8080/api/v1/messages/chat/{chatId}/unread-count
    @GetMapping("/chat/{chatId}/unread-count")
    public ResponseEntity<Long> getUnreadCount(@PathVariable Long chatId) {
        return ResponseEntity.ok(messageService.getUnreadCount(chatId));
    }

    // PATCH http://localhost:8080/api/v1/messages/{id}/status?status=READ
    @PatchMapping("/{id}/status")
    public ResponseEntity<MessageResponse> updateStatus(@PathVariable Long id,
                                                        @RequestParam Message.MessageStatus status) {
        return ResponseEntity.ok(messageService.updateMessageStatus(id, status));
    }

    // PATCH http://localhost:8080/api/v1/messages/{id}/edit?content=new text
    @PatchMapping("/{id}/edit")
    public ResponseEntity<MessageResponse> editMessage(@PathVariable Long id,
                                                       @RequestParam String content) {
        return ResponseEntity.ok(messageService.editMessage(id, content));
    }

    // PATCH http://localhost:8080/api/v1/messages/chat/{chatId}/read-all?userId=2
    @PatchMapping("/chat/{chatId}/read-all")
    public ResponseEntity<Void> markAllAsRead(@PathVariable Long chatId,
                                              @RequestParam Long userId) {
        messageService.markAllMessagesAsRead(chatId, userId);
        return ResponseEntity.noContent().build();
    }

    // DELETE http://localhost:8080/api/v1/messages/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}