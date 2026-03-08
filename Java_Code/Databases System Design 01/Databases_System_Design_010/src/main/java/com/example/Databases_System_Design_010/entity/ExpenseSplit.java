package com.example.Databases_System_Design_010.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense_splits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id", nullable = false)
    private Expense expense;

    // Who owes this share
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Exact amount this user owes for this expense
    @Column(nullable = false, name = "share_amount")
    private Double shareAmount;

    // For PERCENTAGE split type
    @Column(name = "share_percentage")
    private Double sharePercentage;

    // Whether this user has settled this split
    @Column(nullable = false)
    private Boolean settled = false;
}

