package com.example.Databases_System_Design_014.api.subscription.service;

import com.example.Databases_System_Design_014.api.subscription.dto.SubscriptionRequest;
import com.example.Databases_System_Design_014.api.subscription.dto.SubscriptionResponse;
import com.example.Databases_System_Design_014.api.subscription.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponse createSubscription(SubscriptionRequest request);

    SubscriptionResponse getSubscriptionById(Long id);

    List<SubscriptionResponse> getSubscriptionsByUser(Long userId);

    SubscriptionResponse getActiveSubscription(Long userId);

    List<SubscriptionResponse> getAllSubscriptions();

    List<SubscriptionResponse> getSubscriptionsByStatus(Subscription.SubscriptionStatus status);

    SubscriptionResponse cancelSubscription(Long id);

    SubscriptionResponse pauseSubscription(Long id);

    SubscriptionResponse resumeSubscription(Long id);

    List<SubscriptionResponse> getExpiredSubscriptions();
}