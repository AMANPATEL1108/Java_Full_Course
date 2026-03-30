package com.example.Databases_System_Design_015.comment.service.impl;

import com.example.Databases_System_Design_015.comment.dto.CommentRequest;
import com.example.Databases_System_Design_015.comment.dto.CommentResponse;
import com.example.Databases_System_Design_015.comment.entity.Comment;
import com.example.Databases_System_Design_015.comment.repository.CommentRepository;
import com.example.Databases_System_Design_015.comment.service.CommentService;
import com.example.Databases_System_Design_015.tweet.entity.Tweet;
import com.example.Databases_System_Design_015.tweet.repository.TweetRepository;
import com.example.Databases_System_Design_015.user.entity.User;
import com.example.Databases_System_Design_015.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @Override
    public CommentResponse addComment(CommentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));
        Tweet tweet = tweetRepository.findById(request.getTweetId())
                .orElseThrow(() -> new RuntimeException("Tweet not found: " + request.getTweetId()));

        Comment comment = Comment.builder()
                .user(user)
                .tweet(tweet)
                .content(request.getContent())
                .isActive(true)
                .build();

        if (request.getParentCommentId() != null) {
            Comment parent = commentRepository.findById(request.getParentCommentId())
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
            comment.setParentComment(parent);
        }

        Comment saved = commentRepository.save(comment);

        tweet.setCommentsCount(tweet.getCommentsCount() + 1);
        tweetRepository.save(tweet);

        return CommentResponse.from(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponse getCommentById(Long id) {
        return CommentResponse.from(findCommentOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByTweet(Long tweetId) {
        return commentRepository
                .findByTweetIdAndIsActiveAndParentCommentIsNullOrderByCreatedAtDesc(tweetId, true)
                .stream().map(CommentResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getRepliesByComment(Long commentId) {
        return commentRepository.findByParentCommentIdAndIsActive(commentId, true)
                .stream().map(CommentResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByUser(Long userId) {
        return commentRepository.findByUserIdAndIsActive(userId, true)
                .stream().map(CommentResponse::from).collect(Collectors.toList());
    }

    @Override
    public CommentResponse updateComment(Long id, CommentRequest request) {
        Comment comment = findCommentOrThrow(id);
        comment.setContent(request.getContent());
        return CommentResponse.from(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = findCommentOrThrow(id);
        comment.setIsActive(false);
        commentRepository.save(comment);

        Tweet tweet = comment.getTweet();
        if (tweet.getCommentsCount() > 0) {
            tweet.setCommentsCount(tweet.getCommentsCount() - 1);
            tweetRepository.save(tweet);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCommentsCount(Long tweetId) {
        return commentRepository.countByTweetIdAndIsActive(tweetId, true);
    }

    private Comment findCommentOrThrow(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }
}