package com.tthttl.customerservice.owner.service;

import com.tthttl.customerservice.AbstractMapService;
import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.pet.service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("map")
@Service
public class OwnerServiceMapImpl extends AbstractMapService<Owner> implements OwnerService {

    private PetService petService;

    public OwnerServiceMapImpl(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Owner save(Owner owner) {
        if (owner.getId() == null) {
            owner.setId(map.isEmpty() ? 1 : Collections.max(map.keySet()) + 1);
        }
        owner.setPets(
                owner.getPets().stream()
                        .map(pet -> {
                            if (pet.getId() == null) {
                                pet.setOwner(owner);
                                return petService.save(pet);
                            }
                            return pet;
                        })
                        .collect(Collectors.toSet())
        );
        return super.save(owner, owner.getId());
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return super.findAll();
    }
}
