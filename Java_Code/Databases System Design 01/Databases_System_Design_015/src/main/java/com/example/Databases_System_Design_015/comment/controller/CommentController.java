package com.example.Databases_System_Design_015.comment.controller;

import com.example.Databases_System_Design_015.comment.dto.CommentRequest;
import com.example.Databases_System_Design_015.comment.dto.CommentResponse;
import com.example.Databases_System_Design_015.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // POST http://localhost:8080/api/v1/comments
    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@Valid @RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.addComment(request));
    }

    // GET http://localhost:8080/api/v1/comments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    // GET http://localhost:8080/api/v1/comments/tweet/{tweetId}
    @GetMapping("/tweet/{tweetId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByTweet(@PathVariable Long tweetId) {
        return ResponseEntity.ok(commentService.getCommentsByTweet(tweetId));
    }

    // GET http://localhost:8080/api/v1/comments/{commentId}/replies
    @GetMapping("/{commentId}/replies")
    public ResponseEntity<List<CommentResponse>> getReplies(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getRepliesByComment(commentId));
    }

    // GET http://localhost:8080/api/v1/comments/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(commentService.getCommentsByUser(userId));
    }

    // GET http://localhost:8080/api/v1/comments/tweet/{tweetId}/count
    @GetMapping("/tweet/{tweetId}/count")
    public ResponseEntity<Long> getCommentsCount(@PathVariable Long tweetId) {
        return ResponseEntity.ok(commentService.getCommentsCount(tweetId));
    }

    // PUT http://localhost:8080/api/v1/comments/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id,
                                                         @Valid @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(id, request));
    }

    // DELETE http://localhost:8080/api/v1/comments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}