package com.example.Databases_System_Design_015.tweet.entity;

import com.example.Databases_System_Design_015.user.entity.User;
import com.example.Databases_System_Design_015.like.entity.Like;
import com.example.Databases_System_Design_015.comment.entity.Comment;
import com.example.Databases_System_Design_015.hashtag.entity.Hashtag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tweets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 280)
    private String content;

    @Column(length = 500)
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TweetType tweetType = TweetType.ORIGINAL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_tweet_id")
    private Tweet parentTweet;

    @OneToMany(mappedBy = "parentTweet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tweet> retweets;

    private Long likesCount = 0L;

    private Long retweetsCount = 0L;

    private Long commentsCount = 0L;

    private Long viewsCount = 0L;

    private Boolean isActive = true;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tweet_hashtags",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private List<Hashtag> hashtags;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.likesCount == null) this.likesCount = 0L;
        if (this.retweetsCount == null) this.retweetsCount = 0L;
        if (this.commentsCount == null) this.commentsCount = 0L;
        if (this.viewsCount == null) this.viewsCount = 0L;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum TweetType {
        ORIGINAL, RETWEET, QUOTE_TWEET, REPLY
    }
}