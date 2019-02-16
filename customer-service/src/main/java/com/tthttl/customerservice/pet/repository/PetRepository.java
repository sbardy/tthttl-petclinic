package com.tthttl.customerservice.pet.repository;

import com.tthttl.customerservice.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {



}
