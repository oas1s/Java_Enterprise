package ru.itis.pets.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.pets.entity.Pet;

import java.util.List;

public interface PetRepo extends CrudRepository<Pet, Long> {

    List<Pet> findAll();
}
