package com.example.Databases_System_Design_010.serviceImpl;


import com.example.Databases_System_Design_010.dto.request.SettlementRequest;
import com.example.Databases_System_Design_010.dto.response.SettlementResponse;
import com.example.Databases_System_Design_010.repository.*;
import com.example.Databases_System_Design_010.service.SettlementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository settlementRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseSplitRepository expenseSplitRepository;

    public SettlementServiceImpl(SettlementRepository settlementRepository,
                                 PaymentRepository paymentRepository,
                                 UserRepository userRepository,
                                 GroupRepository groupRepository,
                                 ExpenseSplitRepository expenseSplitRepository) {
        this.settlementRepository = settlementRepository;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseSplitRepository = expenseSplitRepository;
    }

    @Override
    @Transactional
    public SettlementResponse settleUp(SettlementRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public SettlementResponse getSettlementById(Long settlementId) {
        // TODO: implement
        return null;
    }

    @Override
    public List<SettlementResponse> getSettlementsByGroup(Long groupId) {
        // TODO: implement
        return null;
    }

    @Override
    public List<SettlementResponse> getSettlementsByUser(Long userId) {
        // TODO: implement
        return null;
    }
}