package com.example.Databases_System_Design_016.chat.controller;

import com.example.Databases_System_Design_016.chat.dto.ChatResponse;
import com.example.Databases_System_Design_016.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // POST http://localhost:8080/api/v1/chats/private?senderId=1&receiverId=2
    @PostMapping("/private")
    public ResponseEntity<ChatResponse> createPrivateChat(@RequestParam Long senderId,
                                                          @RequestParam Long receiverId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatService.createPrivateChat(senderId, receiverId));
    }

    // POST http://localhost:8080/api/v1/chats/group/{groupId}
    @PostMapping("/group/{groupId}")
    public ResponseEntity<ChatResponse> createGroupChat(@PathVariable Long groupId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatService.createGroupChat(groupId));
    }

    // GET http://localhost:8080/api/v1/chats/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ChatResponse> getChatById(@PathVariable Long id) {
        return ResponseEntity.ok(chatService.getChatById(id));
    }

    // GET http://localhost:8080/api/v1/chats/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatResponse>> getAllChatsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(chatService.getAllChatsForUser(userId));
    }

    // GET http://localhost:8080/api/v1/chats/user/{userId}/private
    @GetMapping("/user/{userId}/private")
    public ResponseEntity<List<ChatResponse>> getPrivateChats(@PathVariable Long userId) {
        return ResponseEntity.ok(chatService.getPrivateChatsForUser(userId));
    }

    // GET http://localhost:8080/api/v1/chats/user/{userId}/groups
    @GetMapping("/user/{userId}/groups")
    public ResponseEntity<List<ChatResponse>> getGroupChats(@PathVariable Long userId) {
        return ResponseEntity.ok(chatService.getGroupChatsForUser(userId));
    }

    // PATCH http://localhost:8080/api/v1/chats/{id}/read
    @PatchMapping("/{id}/read")
    public ResponseEntity<ChatResponse> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(chatService.markChatAsRead(id));
    }

    // DELETE http://localhost:8080/api/v1/chats/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }
}