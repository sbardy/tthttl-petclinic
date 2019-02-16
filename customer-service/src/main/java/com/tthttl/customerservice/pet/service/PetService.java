package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.pet.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    Pet save(Pet pet);

    Optional<Pet> findById(Long id);

    List<Pet> findAll();

    void delete(Pet pet);

}
