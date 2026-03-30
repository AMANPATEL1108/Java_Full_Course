package com.example.Databases_System_Design_015.comment.repository;

import com.example.Databases_System_Design_015.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTweetIdAndIsActiveAndParentCommentIsNullOrderByCreatedAtDesc(
            Long tweetId, Boolean isActive);

    List<Comment> findByUserIdAndIsActive(Long userId, Boolean isActive);

    List<Comment> findByParentCommentIdAndIsActive(Long parentCommentId, Boolean isActive);

    Long countByTweetIdAndIsActive(Long tweetId, Boolean isActive);
}