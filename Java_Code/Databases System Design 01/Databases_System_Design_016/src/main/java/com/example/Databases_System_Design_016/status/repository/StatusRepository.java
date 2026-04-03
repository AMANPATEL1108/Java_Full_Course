package com.example.Databases_System_Design_016.status.repository;

import com.example.Databases_System_Design_016.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    List<Status> findByUserIdAndIsActiveAndExpiresAtAfterOrderByCreatedAtDesc(
            Long userId, Boolean isActive, LocalDateTime now);

    @Query("SELECT s FROM Status s WHERE s.user.id IN :userIds " +
            "AND s.isActive = true AND s.expiresAt > :now ORDER BY s.createdAt DESC")
    List<Status> findActiveStatusesByUserIds(List<Long> userIds, LocalDateTime now);

    List<Status> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("SELECT s FROM Status s WHERE s.isActive = true AND s.expiresAt <= :now")
    List<Status> findExpiredStatuses(LocalDateTime now);
}