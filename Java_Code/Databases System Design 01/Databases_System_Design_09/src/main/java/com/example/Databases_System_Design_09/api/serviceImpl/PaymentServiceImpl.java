package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.repository.PaymentRepository;
import com.example.Databases_System_Design_09.api.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentService;

    PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentService = paymentRepository;
    }
}
