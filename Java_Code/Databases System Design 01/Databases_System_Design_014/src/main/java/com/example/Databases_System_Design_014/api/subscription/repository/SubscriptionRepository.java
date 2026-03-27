package com.example.Databases_System_Design_014.api.subscription.repository;

import com.example.Databases_System_Design_014.api.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUserId(Long userId);

    Optional<Subscription> findByUserIdAndStatus(Long userId, Subscription.SubscriptionStatus status);

    List<Subscription> findByStatus(Subscription.SubscriptionStatus status);

    List<Subscription> findByEndDateBefore(LocalDate date);

    List<Subscription> findByPlanType(Subscription.PlanType planType);
}