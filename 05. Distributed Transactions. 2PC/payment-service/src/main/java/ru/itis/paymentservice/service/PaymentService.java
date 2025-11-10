package ru.itis.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.paymentservice.entity.Payment;
import ru.itis.paymentservice.repository.PaymentRepo;

@Service
public class PaymentService {
    private final PaymentRepo repo;

    @Autowired
    public PaymentService(PaymentRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public void createPayment(Payment payment) {
        repo.save(payment);
    }
}
