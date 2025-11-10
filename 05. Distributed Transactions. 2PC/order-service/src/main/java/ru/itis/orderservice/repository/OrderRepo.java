package ru.itis.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.orderservice.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {}
