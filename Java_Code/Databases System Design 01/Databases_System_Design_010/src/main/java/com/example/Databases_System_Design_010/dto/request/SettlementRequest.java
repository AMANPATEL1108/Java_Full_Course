package com.example.Databases_System_Design_010.dto.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SettlementRequest {

    @NotNull(message = "Payer user ID is required")
    private Long payerId;

    @NotNull(message = "Receiver user ID is required")
    private Long receiverId;

    @NotNull(message = "Group ID is required")
    private Long groupId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;   // UPI, CASH, CARD, NET_BANKING

    private String transactionRef;
}