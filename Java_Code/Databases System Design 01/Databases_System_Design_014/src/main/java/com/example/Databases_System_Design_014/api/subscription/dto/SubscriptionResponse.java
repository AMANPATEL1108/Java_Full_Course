package com.example.Databases_System_Design_014.api.subscription.dto;

import com.example.Databases_System_Design_014.api.subscription.entity.Subscription;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class SubscriptionResponse {

    private Long id;
    private Long userId;
    private String userFullName;
    private Subscription.PlanType planType;
    private Subscription.BillingCycle billingCycle;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Subscription.SubscriptionStatus status;
    private Integer maxScreens;
    private Boolean isHD;
    private Boolean is4K;
    private LocalDateTime createdAt;

    public static SubscriptionResponse from(Subscription s) {
        return SubscriptionResponse.builder()
                .id(s.getId())
                .userId(s.getUser().getId())
                .userFullName(s.getUser().getFullName())
                .planType(s.getPlanType())
                .billingCycle(s.getBillingCycle())
                .price(s.getPrice())
                .startDate(s.getStartDate())
                .endDate(s.getEndDate())
                .status(s.getStatus())
                .maxScreens(s.getMaxScreens())
                .isHD(s.getIsHD())
                .is4K(s.getIs4K())
                .createdAt(s.getCreatedAt())
                .build();
    }
}