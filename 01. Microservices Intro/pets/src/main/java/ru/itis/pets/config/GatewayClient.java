package ru.itis.pets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GatewayClient {

    @Autowired
    @LoadBalanced
    private WebClient.Builder webClientBuilder;

    public String callGateway() {
        return webClientBuilder.build()
                .get()
                .uri("http://gateway/actuator/health")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

