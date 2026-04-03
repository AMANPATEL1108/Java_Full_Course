package com.example.Databases_System_Design_016.status.entity;

import com.example.Databases_System_Design_016.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "statuses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType statusType;

    @Column(columnDefinition = "TEXT")
    private String caption;

    @Column(length = 500)
    private String mediaUrl;

    @Column(length = 7)
    private String backgroundColor;

    @Column(length = 100)
    private String fontStyle;

    private Long viewCount = 0L;

    private Boolean isActive = true;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @PrePersist
    public void prePersist() {
        this.createdAt  = LocalDateTime.now();
        this.expiresAt  = LocalDateTime.now().plusHours(24);
        if (this.viewCount == null) this.viewCount = 0L;
    }

    public enum StatusType {
        TEXT, IMAGE, VIDEO
    }
}