package ru.itis.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.paymentservice.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
