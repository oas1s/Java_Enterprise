package ru.itis.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.demo.entity.OutboxEvent;
import ru.itis.demo.repo.OutboxRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxRelayService {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void relayEvents() {
        List<OutboxEvent> events = outboxRepository.findTop10BySentFalseOrderByCreatedAtAsc();
        for (OutboxEvent event : events) {
            try {
                kafkaTemplate.send("order-events", event.getPayload()).get();
                event.setSent(true);
                outboxRepository.save(event);
                log.info("Sent event id={} to Kafka", event.getId());
            } catch (Exception e) {
                log.error("Failed to send event id={}", event.getId(), e);
            }
        }
    }
}
