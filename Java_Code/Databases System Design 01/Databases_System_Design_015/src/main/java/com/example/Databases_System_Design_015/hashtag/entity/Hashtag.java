package com.example.Databases_System_Design_015.hashtag.entity;

import com.example.Databases_System_Design_015.tweet.entity.Tweet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hashtags")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String tag;

    private Long usageCount = 0L;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "hashtags", fetch = FetchType.LAZY)
    private List<Tweet> tweets;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.usageCount == null) this.usageCount = 0L;
    }
}