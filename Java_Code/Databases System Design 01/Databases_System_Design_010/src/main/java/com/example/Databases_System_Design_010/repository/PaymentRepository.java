package com.example.Databases_System_Design_010.repository;

import com.example.Databases_System_Design_010.entity.Payment;
import com.example.Databases_System_Design_010.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findBySettlement(Settlement settlement);
    Optional<Payment> findByUuid(UUID uuid);
}