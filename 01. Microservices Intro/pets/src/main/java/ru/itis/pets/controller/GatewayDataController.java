package ru.itis.pets.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pets.config.GatewayClient;
import ru.itis.pets.entity.Pet;

@RestController
@RequiredArgsConstructor
public class GatewayDataController {

    private final GatewayClient gatewayClient;

    @GetMapping("/gatewayData")
    public String getPets(){
        return gatewayClient.callGateway();
    }
}
