package com.example.Databases_System_Design_010.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementResponse {
    private UUID uuid;
    private UUID payerUuid;
    private String payerName;
    private UUID receiverUuid;
    private String receiverName;
    private UUID groupUuid;
    private String groupName;
    private Double amount;
    private String status;
    private String paymentMethod;
    private String transactionRef;
    private LocalDateTime settledAt;
    private LocalDateTime createdAt;
}