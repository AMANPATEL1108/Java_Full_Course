package com.example.Databases_System_Design_015.like.entity;

import com.example.Databases_System_Design_015.user.entity.User;
import com.example.Databases_System_Design_015.tweet.entity.Tweet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "tweet_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    @Column(updatable = false)
    private LocalDateTime likedAt;

    @PrePersist
    public void prePersist() {
        this.likedAt = LocalDateTime.now();
    }
}