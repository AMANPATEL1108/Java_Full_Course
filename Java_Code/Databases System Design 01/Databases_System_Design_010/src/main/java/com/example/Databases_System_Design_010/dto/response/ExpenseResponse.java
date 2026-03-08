package com.example.Databases_System_Design_010.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {
    private Long id;
    private UUID uuid;
    private String description;
    private Double amount;
    private String category;
    private String splitType;
    private String paidByName;
    private String paidByEmail;
    private String groupName;
    private LocalDateTime createdAt;
    private List<SplitDetail> splits;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SplitDetail {
        private String userName;
        private String userEmail;
        private Double shareAmount;
        private Double sharePercentage;
        private Boolean settled;
    }
}