package com.example.Databases_System_Design_010.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementResponse {
    private Long id;
    private UUID uuid;
    private String payerName;
    private String payerEmail;
    private String receiverName;
    private String receiverEmail;
    private String groupName;
    private Double amount;
    private String status;
    private String paymentMethod;
    private String transactionRef;
    private LocalDateTime createdAt;
    private LocalDateTime settledAt;
}