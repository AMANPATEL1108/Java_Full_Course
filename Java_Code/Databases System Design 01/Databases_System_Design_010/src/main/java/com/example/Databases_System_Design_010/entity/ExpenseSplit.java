package com.example.Databases_System_Design_010.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expense_splits")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id", nullable = false)
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, name = "share_amount")
    private Double shareAmount;

    @Column(name = "share_percentage")
    private Double sharePercentage;

    @Column(nullable = false)
    private Boolean settled = false;
}