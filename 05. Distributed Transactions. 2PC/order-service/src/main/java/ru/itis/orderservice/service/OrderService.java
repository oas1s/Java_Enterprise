package ru.itis.orderservice.service;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.orderservice.entity.Order;
import ru.itis.orderservice.entity.Payment;
import ru.itis.orderservice.repository.OrderRepo;


@Service
public class OrderService {
    private final OrderRepo repo;
    private final RestTemplate rest = new RestTemplate();

    @Autowired
    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    @GlobalTransactional(name = "create-order-payment", rollbackFor = Exception.class)
    public void createOrder(Order order) {
        repo.save(order);

        String xid = RootContext.getXID(); // текущая глобальная транзакция
        HttpHeaders headers = new HttpHeaders();
        headers.add(RootContext.KEY_XID, xid);

        HttpEntity<Payment> entity = new HttpEntity<>(new Payment(order.getId(), 100.0), headers);
        rest.postForEntity("http://localhost:8082/payments", entity, Void.class);
//        rest.postForEntity("http://localhost:8082/payments", new Payment(order.getId(), 100.0), Void.class);
        if (order.getItem().equals("fail")) {
            throw new RuntimeException("Simulated failure");
        }
    }
}
