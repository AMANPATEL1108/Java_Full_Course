package com.example.Databases_System_Design_010.serviceImpl;

import com.example.Databases_System_Design_010.dto.request.ExpenseRequest;
import com.example.Databases_System_Design_010.dto.request.ExpenseSplitRequest;
import com.example.Databases_System_Design_010.dto.response.ExpenseResponse;
import com.example.Databases_System_Design_010.entity.*;
import com.example.Databases_System_Design_010.enumTypes.SplitType;
import com.example.Databases_System_Design_010.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_010.exception.UnauthorizedAccessException;
import com.example.Databases_System_Design_010.repository.*;
import com.example.Databases_System_Design_010.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ExpenseResponse addExpense(ExpenseRequest request, User currentUser) {
        Group group = groupRepository.findByUuid(request.getGroupUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + request.getGroupUuid()));

        User paidBy = userRepository.findByUuid(request.getPaidByUserUuid())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with UUID: " + request.getPaidByUserUuid()));

        // Verify paidBy is a group member
        if (!groupMemberRepository.existsByGroupAndUser(group, paidBy)) {
            throw new IllegalArgumentException("The paying user is not a member of this group");
        }

        Expense expense = Expense.builder()
                .description(request.getDescription())
                .amount(request.getAmount())
                .category(request.getCategory())
                .splitType(request.getSplitType())
                .paidBy(paidBy)
                .group(group)
                .build();

        expense = expenseRepository.save(expense);

        List<GroupMember> members = groupMemberRepository.findByGroup(group);
        List<ExpenseSplit> splits = createSplits(expense, members, request);
        expenseSplitRepository.saveAll(splits);

        return mapToResponse(expense, splits);
    }

    private List<ExpenseSplit> createSplits(Expense expense, List<GroupMember> members, ExpenseRequest request) {
        List<ExpenseSplit> splits = new ArrayList<>();
        int memberCount = members.size();

        if (request.getSplitType() == SplitType.EQUAL) {
            double shareAmount = Math.round((expense.getAmount() / memberCount) * 100.0) / 100.0;
            for (GroupMember member : members) {
                splits.add(ExpenseSplit.builder()
                        .expense(expense)
                        .user(member.getUser())
                        .shareAmount(shareAmount)
                        .settled(false)
                        .build());
            }

        } else if (request.getSplitType() == SplitType.EXACT) {
            if (request.getSplits() == null || request.getSplits().isEmpty()) {
                throw new IllegalArgumentException("Splits are required for EXACT split type");
            }
            double total = request.getSplits().stream().mapToDouble(ExpenseSplitRequest::getExactAmount).sum();
            if (Math.abs(total - expense.getAmount()) > 0.01) {
                throw new IllegalArgumentException("Sum of exact amounts (" + total + ") must equal expense amount (" + expense.getAmount() + ")");
            }
            for (ExpenseSplitRequest splitReq : request.getSplits()) {
                User user = userRepository.findByUuid(splitReq.getUserUuid())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found: " + splitReq.getUserUuid()));
                splits.add(ExpenseSplit.builder()
                        .expense(expense)
                        .user(user)
                        .shareAmount(splitReq.getExactAmount())
                        .settled(false)
                        .build());
            }

        } else if (request.getSplitType() == SplitType.PERCENTAGE) {
            if (request.getSplits() == null || request.getSplits().isEmpty()) {
                throw new IllegalArgumentException("Splits are required for PERCENTAGE split type");
            }
            double totalPercentage = request.getSplits().stream().mapToDouble(ExpenseSplitRequest::getPercentage).sum();
            if (Math.abs(totalPercentage - 100.0) > 0.01) {
                throw new IllegalArgumentException("Total percentage must equal 100, got: " + totalPercentage);
            }
            for (ExpenseSplitRequest splitReq : request.getSplits()) {
                User user = userRepository.findByUuid(splitReq.getUserUuid())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found: " + splitReq.getUserUuid()));
                double shareAmount = Math.round((expense.getAmount() * splitReq.getPercentage() / 100.0) * 100.0) / 100.0;
                splits.add(ExpenseSplit.builder()
                        .expense(expense)
                        .user(user)
                        .shareAmount(shareAmount)
                        .sharePercentage(splitReq.getPercentage())
                        .settled(false)
                        .build());
            }
        }

        return splits;
    }

    @Override
    public ExpenseResponse getExpenseByUuid(UUID expenseUuid) {
        Expense expense = expenseRepository.findByUuid(expenseUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with UUID: " + expenseUuid));
        List<ExpenseSplit> splits = expenseSplitRepository.findByExpense(expense);
        return mapToResponse(expense, splits);
    }

    @Override
    public List<ExpenseResponse> getExpensesByGroup(UUID groupUuid) {
        Group group = groupRepository.findByUuid(groupUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + groupUuid));
        return expenseRepository.findByGroup(group).stream()
                .map(e -> mapToResponse(e, expenseSplitRepository.findByExpense(e)))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseResponse> getExpensesByUser(UUID userUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with UUID: " + userUuid));
        return expenseRepository.findByPaidBy(user).stream()
                .map(e -> mapToResponse(e, expenseSplitRepository.findByExpense(e)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteExpense(UUID expenseUuid, User currentUser) {
        Expense expense = expenseRepository.findByUuid(expenseUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with UUID: " + expenseUuid));

        // Only the payer or group creator can delete
        boolean isPayer = expense.getPaidBy().getId().equals(currentUser.getId());
        boolean isCreator = expense.getGroup().getCreatedBy().getId().equals(currentUser.getId());

        if (!isPayer && !isCreator) {
            throw new UnauthorizedAccessException("Only the expense payer or group creator can delete this expense");
        }

        expenseSplitRepository.deleteAll(expenseSplitRepository.findByExpense(expense));
        expenseRepository.delete(expense);
    }

    private ExpenseResponse mapToResponse(Expense expense, List<ExpenseSplit> splits) {
        List<ExpenseResponse.SplitDetail> splitDetails = splits.stream()
                .map(s -> ExpenseResponse.SplitDetail.builder()
                        .userUuid(s.getUser().getUuid())
                        .userName(s.getUser().getName())
                        .shareAmount(s.getShareAmount())
                        .sharePercentage(s.getSharePercentage())
                        .settled(s.getSettled())
                        .build())
                .collect(Collectors.toList());

        return ExpenseResponse.builder()
                .uuid(expense.getUuid())
                .description(expense.getDescription())
                .amount(expense.getAmount())
                .category(expense.getCategory().name())
                .splitType(expense.getSplitType().name())
                .groupUuid(expense.getGroup().getUuid())
                .groupName(expense.getGroup().getName())
                .paidByUuid(expense.getPaidBy().getUuid())
                .paidByName(expense.getPaidBy().getName())
                .createdAt(expense.getCreatedAt())
                .splits(splitDetails)
                .build();
    }
}