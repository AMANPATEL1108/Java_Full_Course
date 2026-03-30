package com.example.Databases_System_Design_015.follow.repository;

import com.example.Databases_System_Design_015.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowerIdAndFollowingId(Long followerId, Long followingId);

    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);

    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);

    List<Follow> findByFollowerId(Long followerId);

    List<Follow> findByFollowingId(Long followingId);

    @Query("SELECT f.following.id FROM Follow f WHERE f.follower.id = :userId")
    List<Long> findFollowingIdsByUserId(Long userId);

    Long countByFollowerId(Long followerId);

    Long countByFollowingId(Long followingId);
}