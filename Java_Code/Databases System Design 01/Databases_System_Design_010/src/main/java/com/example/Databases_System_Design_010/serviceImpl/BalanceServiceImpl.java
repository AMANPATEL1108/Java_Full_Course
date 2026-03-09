package com.example.Databases_System_Design_010.serviceImpl;

import com.example.Databases_System_Design_010.dto.response.BalanceSummaryResponse;
import com.example.Databases_System_Design_010.dto.response.UserBalanceResponse;
import com.example.Databases_System_Design_010.entity.*;
import com.example.Databases_System_Design_010.enumTypes.SettlementStatus;
import com.example.Databases_System_Design_010.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_010.repository.*;
import com.example.Databases_System_Design_010.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;
    private final SettlementRepository settlementRepository;

    @Override
    public BalanceSummaryResponse getGroupBalanceSummary(UUID groupUuid, User currentUser) {
        Group group = groupRepository.findByUuid(groupUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + groupUuid));

        List<Expense> expenses = expenseRepository.findByGroup(group);
        List<ExpenseSplit> allSplits = expenseSplitRepository.findByGroupUuid(groupUuid);

        // Calculate total expenses in group
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();

        // My total share (what I owe across all expenses)
        double myTotalShare = allSplits.stream()
                .filter(s -> s.getUser().getId().equals(currentUser.getId()))
                .mapToDouble(ExpenseSplit::getShareAmount)
                .sum();

        // Total I paid
        double myTotalPaid = expenses.stream()
                .filter(e -> e.getPaidBy().getId().equals(currentUser.getId()))
                .mapToDouble(Expense::getAmount)
                .sum();

        double netBalance = myTotalPaid - myTotalShare;

        // Per-user balance: net[otherUser] = what other owes me - what I owe other
        Map<Long, Double> netBalanceMap = new HashMap<>();

        for (ExpenseSplit split : allSplits) {
            User splitUser = split.getUser();
            Expense expense = split.getExpense();
            User paidBy = expense.getPaidBy();

            if (!paidBy.getId().equals(splitUser.getId())) {
                // splitUser owes paidBy this share amount
                if (paidBy.getId().equals(currentUser.getId())) {
                    // someone owes ME
                    netBalanceMap.merge(splitUser.getId(), split.getShareAmount(), Double::sum);
                } else if (splitUser.getId().equals(currentUser.getId())) {
                    // I owe someone
                    netBalanceMap.merge(paidBy.getId(), -split.getShareAmount(), Double::sum);
                }
            }
        }

        // Adjust for settlements in this group
        List<Settlement> settlements = settlementRepository.findByGroup(group);
        for (Settlement settlement : settlements) {
            if (settlement.getStatus() == SettlementStatus.COMPLETED) {
                if (settlement.getPayer().getId().equals(currentUser.getId())) {
                    // I paid someone — reduce what I owe them
                    netBalanceMap.merge(settlement.getReceiver().getId(), settlement.getAmount(), Double::sum);
                } else if (settlement.getReceiver().getId().equals(currentUser.getId())) {
                    // Someone paid me — reduce what they owe me
                    netBalanceMap.merge(settlement.getPayer().getId(), -settlement.getAmount(), Double::sum);
                }
            }
        }

        // Build per-user balance list
        List<GroupMember> members = groupMemberRepository.findByGroup(group);
        Map<Long, User> memberMap = members.stream()
                .collect(Collectors.toMap(m -> m.getUser().getId(), GroupMember::getUser));

        List<UserBalanceResponse> balances = netBalanceMap.entrySet().stream()
                .filter(e -> Math.abs(e.getValue()) > 0.01)
                .map(e -> {
                    User otherUser = memberMap.get(e.getKey());
                    if (otherUser == null) return null;
                    double amount = e.getValue();
                    return UserBalanceResponse.builder()
                            .withUserUuid(otherUser.getUuid())
                            .withUserName(otherUser.getName())
                            .withUserEmail(otherUser.getEmail())
                            .amount(Math.abs(amount))
                            .status(amount > 0 ? "THEY OWE YOU" : "YOU OWE")
                            .build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return BalanceSummaryResponse.builder()
                .groupUuid(group.getUuid())
                .groupName(group.getName())
                .totalExpenses(totalExpenses)
                .yourTotalShare(myTotalShare)
                .yourTotalPaid(myTotalPaid)
                .netBalance(netBalance)
                .balances(balances)
                .build();
    }

    @Override
    public List<UserBalanceResponse> getOverallBalances(User currentUser) {
        // Get all expense splits involving current user
        List<ExpenseSplit> mySplits = expenseSplitRepository.findByUser(currentUser);

        Map<Long, Double> netBalanceMap = new HashMap<>();
        Map<Long, User> userMap = new HashMap<>();

        for (ExpenseSplit split : mySplits) {
            User paidBy = split.getExpense().getPaidBy();
            if (!paidBy.getId().equals(currentUser.getId())) {
                // I owe paidBy
                netBalanceMap.merge(paidBy.getId(), -split.getShareAmount(), Double::sum);
                userMap.put(paidBy.getId(), paidBy);
            }
        }

        // Expenses I paid — others owe me
        List<ExpenseSplit> splitsOnMyExpenses = expenseSplitRepository.findByUser(currentUser);
        // Get all splits where I am the payer of the parent expense
        for (User key : userMap.values()) {
            // already handled above
        }

        // Better approach: iterate all my expenses
        List<Expense> myExpenses = expenseRepository.findByPaidBy(currentUser);
        for (Expense expense : myExpenses) {
            List<ExpenseSplit> splits = expenseSplitRepository.findByExpense(expense);
            for (ExpenseSplit split : splits) {
                if (!split.getUser().getId().equals(currentUser.getId())) {
                    // this person owes me
                    netBalanceMap.merge(split.getUser().getId(), split.getShareAmount(), Double::sum);
                    userMap.put(split.getUser().getId(), split.getUser());
                }
            }
        }

        // Adjust for all completed settlements
        List<Settlement> settlements = settlementRepository.findByPayerOrReceiver(currentUser, currentUser);
        for (Settlement settlement : settlements) {
            if (settlement.getStatus() == SettlementStatus.COMPLETED) {
                if (settlement.getPayer().getId().equals(currentUser.getId())) {
                    netBalanceMap.merge(settlement.getReceiver().getId(), settlement.getAmount(), Double::sum);
                    userMap.put(settlement.getReceiver().getId(), settlement.getReceiver());
                } else {
                    netBalanceMap.merge(settlement.getPayer().getId(), -settlement.getAmount(), Double::sum);
                    userMap.put(settlement.getPayer().getId(), settlement.getPayer());
                }
            }
        }

        return netBalanceMap.entrySet().stream()
                .filter(e -> Math.abs(e.getValue()) > 0.01)
                .map(e -> {
                    User other = userMap.get(e.getKey());
                    double amount = e.getValue();
                    return UserBalanceResponse.builder()
                            .withUserUuid(other.getUuid())
                            .withUserName(other.getName())
                            .withUserEmail(other.getEmail())
                            .amount(Math.abs(amount))
                            .status(amount > 0 ? "THEY OWE YOU" : "YOU OWE")
                            .build();
                })
                .collect(Collectors.toList());
    }
}