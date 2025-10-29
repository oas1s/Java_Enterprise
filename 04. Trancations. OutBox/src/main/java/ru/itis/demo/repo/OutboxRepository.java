package ru.itis.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.demo.entity.OutboxEvent;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Long> {
    List<OutboxEvent> findTop10BySentFalseOrderByCreatedAtAsc();
}