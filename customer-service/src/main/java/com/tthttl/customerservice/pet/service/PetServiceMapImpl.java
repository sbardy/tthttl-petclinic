package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.AbstractMapService;
import com.tthttl.customerservice.pet.model.Pet;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Profile("map")
@Service
public class PetServiceMapImpl extends AbstractMapService<Pet> implements PetService {

    @Override
    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(map.isEmpty() ? 1 : Collections.max(map.keySet()) + 1);
        }
        return super.save(pet, pet.getId());
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet pet) {
        super.delete(pet);
    }
}
