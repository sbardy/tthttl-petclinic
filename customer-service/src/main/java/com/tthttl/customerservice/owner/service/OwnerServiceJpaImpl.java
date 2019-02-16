package com.tthttl.customerservice.owner.service;

import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.owner.repository.OwnerRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile("jpa")
@Service
public class OwnerServiceJpaImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceJpaImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
