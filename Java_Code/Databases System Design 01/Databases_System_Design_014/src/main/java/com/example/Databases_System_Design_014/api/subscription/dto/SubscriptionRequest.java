package com.example.Databases_System_Design_014.api.subscription.dto;

import com.example.Databases_System_Design_014.api.subscription.entity.Subscription;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Plan type is required")
    private Subscription.PlanType planType;

    @NotNull(message = "Billing cycle is required")
    private Subscription.BillingCycle billingCycle;
}