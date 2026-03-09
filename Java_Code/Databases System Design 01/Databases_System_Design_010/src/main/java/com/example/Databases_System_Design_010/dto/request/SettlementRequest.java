package com.example.Databases_System_Design_010.dto.request;

import com.example.Databases_System_Design_010.enumTypes.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class SettlementRequest {

    @NotNull(message = "Payer UUID is required")
    private UUID payerUuid;

    @NotNull(message = "Receiver UUID is required")
    private UUID receiverUuid;

    @NotNull(message = "Group UUID is required")
    private UUID groupUuid;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    private String transactionRef;
}