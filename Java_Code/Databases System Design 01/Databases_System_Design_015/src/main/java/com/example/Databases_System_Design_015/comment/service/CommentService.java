package com.example.Databases_System_Design_015.comment.service;

import com.example.Databases_System_Design_015.comment.dto.CommentRequest;
import com.example.Databases_System_Design_015.comment.dto.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentResponse addComment(CommentRequest request);

    CommentResponse getCommentById(Long id);

    List<CommentResponse> getCommentsByTweet(Long tweetId);

    List<CommentResponse> getRepliesByComment(Long commentId);

    List<CommentResponse> getCommentsByUser(Long userId);

    CommentResponse updateComment(Long id, CommentRequest request);

    void deleteComment(Long id);

    Long getCommentsCount(Long tweetId);
}