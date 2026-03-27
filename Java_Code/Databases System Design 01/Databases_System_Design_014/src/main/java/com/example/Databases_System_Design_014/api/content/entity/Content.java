package com.example.Databases_System_Design_014.api.content.entity;

import com.example.Databases_System_Design_014.api.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(length = 10)
    private String maturityRating;   // PG, R, TV-MA, etc.

    private Integer releaseYear;

    private LocalDate releaseDate;

    private Integer durationMinutes;

    private Integer totalSeasons;    // for SERIES

    private Integer totalEpisodes;   // for SERIES

    @Column(length = 500)
    private String thumbnailUrl;

    @Column(length = 500)
    private String trailerUrl;

    @Column(length = 500)
    private String streamUrl;

    private Double averageRating;

    private Long viewCount;

    @Column(nullable = false)
    private Boolean isAvailable = true;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.viewCount == null) this.viewCount = 0L;
        if (this.averageRating == null) this.averageRating = 0.0;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum ContentType {
        MOVIE, SERIES, DOCUMENTARY, SHORT
    }

    public enum Genre {
        ACTION, COMEDY, DRAMA, HORROR, THRILLER,
        ROMANCE, SCI_FI, ANIMATION, CRIME, FANTASY,
        MYSTERY, BIOGRAPHY, SPORTS, MUSICAL
    }
}