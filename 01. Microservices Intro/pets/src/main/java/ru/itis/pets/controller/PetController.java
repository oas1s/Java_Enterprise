package ru.itis.pets.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pets.entity.Pet;
import ru.itis.pets.repository.PetRepo;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PetController {

    private final PetRepo petRepo;

    @GetMapping("/pets")
    public List<Pet> getPets(){
        log.info("Getting all pets");
        return petRepo.findAll();
    }
}
