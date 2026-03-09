package com.example.Databases_System_Design_010.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {
    private UUID uuid;
    private String description;
    private Double amount;
    private String category;
    private String splitType;
    private UUID groupUuid;
    private String groupName;
    private UUID paidByUuid;
    private String paidByName;
    private LocalDateTime createdAt;
    private List<SplitDetail> splits;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SplitDetail {
        private UUID userUuid;
        private String userName;
        private Double shareAmount;
        private Double sharePercentage;
        private Boolean settled;
    }
}