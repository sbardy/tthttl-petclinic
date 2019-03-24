package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.pet.model.PetType;
import com.tthttl.customerservice.pet.repository.PetTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile({"dev", "docker", "h2", "localhost"})
@Service
public class PetTypeServiceJpaImpl implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceJpaImpl(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public List<PetType> findAll() {
        return this.petTypeRepository.findAll();
    }
}
