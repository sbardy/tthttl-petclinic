package com.tthttl.customerservice.owner.service;

import com.tthttl.customerservice.owner.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {

    Owner save(Owner owner);

    void delete(Owner owner);

    Optional<Owner> findById(Long id);

    List<Owner> findAll();

}
