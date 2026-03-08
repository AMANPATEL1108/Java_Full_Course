package com.example.Databases_System_Design_010.repository;

import com.example.Databases_System_Design_010.entity.Group;
import com.example.Databases_System_Design_010.entity.Settlement;
import com.example.Databases_System_Design_010.entity.User;
import com.example.Databases_System_Design_010.enumTypes.SettlementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, Long> {
    List<Settlement> findByGroup(Group group);
    List<Settlement> findByPayer(User payer);
    List<Settlement> findByReceiver(User receiver);
    List<Settlement> findByPayerAndStatus(User payer, SettlementStatus status);
    List<Settlement> findByPayerAndReceiverAndGroup(User payer, User receiver, Group group);
}