package ru.itis.orderservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.orderservice.entity.Order;
import ru.itis.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        service.createOrder(order);
        return "Order created successfully";
    }
}

