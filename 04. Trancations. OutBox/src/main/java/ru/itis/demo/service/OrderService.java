package ru.itis.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.demo.entity.Order;
import ru.itis.demo.entity.OutboxEvent;
import ru.itis.demo.repo.OrderRepository;
import ru.itis.demo.repo.OutboxRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    public Order createOrder(String customer, BigDecimal amount) throws Exception {
        Order order = orderRepository.save(Order.builder()
                .customer(customer)
                .amount(amount)
                .build());
        
        String payload = objectMapper.writeValueAsString(order);
//        kafkaTemplate.send("order-events", order.getId().toString(), payload);
        outboxRepository.save(new OutboxEvent());
        return order;
    }
}
