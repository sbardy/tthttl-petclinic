package com.tthttl.customerservice.pet.repository;

import com.tthttl.customerservice.pet.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}
