package com.example.Databases_System_Design_010.serviceImpl;

import com.example.Databases_System_Design_010.dto.request.SettlementRequest;
import com.example.Databases_System_Design_010.dto.response.SettlementResponse;
import com.example.Databases_System_Design_010.entity.*;
import com.example.Databases_System_Design_010.enumTypes.SettlementStatus;
import com.example.Databases_System_Design_010.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_010.exception.UnauthorizedAccessException;
import com.example.Databases_System_Design_010.repository.*;
import com.example.Databases_System_Design_010.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository settlementRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Override
    @Transactional
    public SettlementResponse settleUp(SettlementRequest request, User currentUser) {
        User payer = userRepository.findByUuid(request.getPayerUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Payer not found with UUID: " + request.getPayerUuid()));

        User receiver = userRepository.findByUuid(request.getReceiverUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Receiver not found with UUID: " + request.getReceiverUuid()));

        Group group = groupRepository.findByUuid(request.getGroupUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + request.getGroupUuid()));

        // Only the payer themselves can initiate settlement
        if (!payer.getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("You can only settle payments where you are the payer");
        }

        // Both must be members
        if (!groupMemberRepository.existsByGroupAndUser(group, payer)) {
            throw new IllegalArgumentException("Payer is not a member of the group");
        }
        if (!groupMemberRepository.existsByGroupAndUser(group, receiver)) {
            throw new IllegalArgumentException("Receiver is not a member of the group");
        }

        Settlement settlement = Settlement.builder()
                .payer(payer)
                .receiver(receiver)
                .group(group)
                .amount(request.getAmount())
                .status(SettlementStatus.COMPLETED)
                .settledAt(LocalDateTime.now())
                .build();

        settlement = settlementRepository.save(settlement);

        // Create payment record
        Payment payment = Payment.builder()
                .settlement(settlement)
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentTime(LocalDateTime.now())
                .transactionRef(request.getTransactionRef())
                .build();

        paymentRepository.save(payment);

        return mapToResponse(settlement, payment);
    }

    @Override
    public SettlementResponse getSettlementByUuid(UUID settlementUuid) {
        Settlement settlement = settlementRepository.findByUuid(settlementUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Settlement not found with UUID: " + settlementUuid));
        Payment payment = paymentRepository.findBySettlement(settlement).orElse(null);
        return mapToResponse(settlement, payment);
    }

    @Override
    public List<SettlementResponse> getSettlementsByGroup(UUID groupUuid) {
        Group group = groupRepository.findByUuid(groupUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with UUID: " + groupUuid));
        return settlementRepository.findByGroup(group).stream()
                .map(s -> mapToResponse(s, paymentRepository.findBySettlement(s).orElse(null)))
                .collect(Collectors.toList());
    }

    @Override
    public List<SettlementResponse> getSettlementsByUser(UUID userUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with UUID: " + userUuid));
        return settlementRepository.findByPayerOrReceiver(user, user).stream()
                .map(s -> mapToResponse(s, paymentRepository.findBySettlement(s).orElse(null)))
                .collect(Collectors.toList());
    }

    private SettlementResponse mapToResponse(Settlement settlement, Payment payment) {
        return SettlementResponse.builder()
                .uuid(settlement.getUuid())
                .payerUuid(settlement.getPayer().getUuid())
                .payerName(settlement.getPayer().getName())
                .receiverUuid(settlement.getReceiver().getUuid())
                .receiverName(settlement.getReceiver().getName())
                .groupUuid(settlement.getGroup().getUuid())
                .groupName(settlement.getGroup().getName())
                .amount(settlement.getAmount())
                .status(settlement.getStatus().name())
                .paymentMethod(payment != null ? payment.getPaymentMethod().name() : null)
                .transactionRef(payment != null ? payment.getTransactionRef() : null)
                .settledAt(settlement.getSettledAt())
                .createdAt(settlement.getCreatedAt())
                .build();
    }
}