package com.example.Databases_System_Design_015.hashtag.repository;

import com.example.Databases_System_Design_015.hashtag.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByTag(String tag);

    boolean existsByTag(String tag);

    List<Hashtag> findByTagContainingIgnoreCase(String keyword);

    @Query("SELECT h FROM Hashtag h ORDER BY h.usageCount DESC")
    List<Hashtag> findTrendingHashtags();
}