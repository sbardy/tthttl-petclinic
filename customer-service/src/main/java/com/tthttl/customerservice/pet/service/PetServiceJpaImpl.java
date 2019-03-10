package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.pet.model.Pet;
import com.tthttl.customerservice.pet.repository.PetRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile({"dev", "docker", "h2", "localhost"})
@Service
public class PetServiceJpaImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceJpaImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }
}
