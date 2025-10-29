package ru.itis.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}