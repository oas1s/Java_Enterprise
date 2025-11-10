package ru.itis.paymentservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.paymentservice.entity.Payment;
import ru.itis.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public String createPayment(@RequestBody Payment payment) {
        service.createPayment(payment);
        return "Payment created successfully";
    }
}
