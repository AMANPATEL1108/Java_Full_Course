package com.example.Databases_System_Design_014.api.subscription.service.impl;

import com.example.Databases_System_Design_014.api.subscription.dto.SubscriptionRequest;
import com.example.Databases_System_Design_014.api.subscription.dto.SubscriptionResponse;
import com.example.Databases_System_Design_014.api.subscription.entity.Subscription;
import com.example.Databases_System_Design_014.api.subscription.repository.SubscriptionRepository;
import com.example.Databases_System_Design_014.api.subscription.service.SubscriptionService;
import com.example.Databases_System_Design_014.api.user.entity.User;
import com.example.Databases_System_Design_014.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        // Ensure user has no active subscription
        subscriptionRepository.findByUserIdAndStatus(request.getUserId(),
                Subscription.SubscriptionStatus.ACTIVE).ifPresent(s -> {
            throw new RuntimeException("User already has an active subscription");
        });

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        PlanDetails details = resolvePlan(request.getPlanType(), request.getBillingCycle());

        LocalDate start = LocalDate.now();
        LocalDate end = request.getBillingCycle() == Subscription.BillingCycle.ANNUALLY
                ? start.plusYears(1)
                : start.plusMonths(1);

        Subscription subscription = Subscription.builder()
                .user(user)
                .planType(request.getPlanType())
                .billingCycle(request.getBillingCycle())
                .price(details.price)
                .startDate(start)
                .endDate(end)
                .status(Subscription.SubscriptionStatus.ACTIVE)
                .maxScreens(details.maxScreens)
                .isHD(details.isHD)
                .is4K(details.is4K)
                .build();

        return SubscriptionResponse.from(subscriptionRepository.save(subscription));
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponse getSubscriptionById(Long id) {
        Subscription s = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
        return SubscriptionResponse.from(s);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> getSubscriptionsByUser(Long userId) {
        return subscriptionRepository.findByUserId(userId).stream()
                .map(SubscriptionResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponse getActiveSubscription(Long userId) {
        Subscription s = subscriptionRepository
                .findByUserIdAndStatus(userId, Subscription.SubscriptionStatus.ACTIVE)
                .orElseThrow(() -> new RuntimeException("No active subscription for user id: " + userId));
        return SubscriptionResponse.from(s);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(SubscriptionResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> getSubscriptionsByStatus(Subscription.SubscriptionStatus status) {
        return subscriptionRepository.findByStatus(status).stream()
                .map(SubscriptionResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionResponse cancelSubscription(Long id) {
        Subscription s = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
        s.setStatus(Subscription.SubscriptionStatus.CANCELLED);
        return SubscriptionResponse.from(subscriptionRepository.save(s));
    }

    @Override
    public SubscriptionResponse pauseSubscription(Long id) {
        Subscription s = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
        s.setStatus(Subscription.SubscriptionStatus.PAUSED);
        return SubscriptionResponse.from(subscriptionRepository.save(s));
    }

    @Override
    public SubscriptionResponse resumeSubscription(Long id) {
        Subscription s = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
        s.setStatus(Subscription.SubscriptionStatus.ACTIVE);
        return SubscriptionResponse.from(subscriptionRepository.save(s));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> getExpiredSubscriptions() {
        return subscriptionRepository.findByEndDateBefore(LocalDate.now()).stream()
                .map(SubscriptionResponse::from)
                .collect(Collectors.toList());
    }

    // ─── Pricing helper ───────────────────────────────────────────────────────
    private PlanDetails resolvePlan(Subscription.PlanType plan, Subscription.BillingCycle cycle) {
        boolean annual = cycle == Subscription.BillingCycle.ANNUALLY;
        return switch (plan) {
            case BASIC    -> new PlanDetails(annual ? new BigDecimal("59.99")  : new BigDecimal("6.99"),  1, false, false);
            case STANDARD -> new PlanDetails(annual ? new BigDecimal("119.99") : new BigDecimal("12.99"), 2, true,  false);
            case PREMIUM  -> new PlanDetails(annual ? new BigDecimal("179.99") : new BigDecimal("18.99"), 4, true,  true);
        };
    }

    private record PlanDetails(BigDecimal price, int maxScreens, boolean isHD, boolean is4K) {}
}