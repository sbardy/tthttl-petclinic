package com.tthttl.customerservice.owner.service;

import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.pet.service.PetServiceMapImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.tthttl.customerservice.CustomerServiceTestsHelper.createOwner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OwnerServiceMapImplTest {

    private OwnerServiceMapImpl testee;

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = createOwner();
        testee = new OwnerServiceMapImpl(new PetServiceMapImpl());
    }

    @Test
    void save() {
        Owner savedOwner = testee.save(owner);

        assertEquals(new Long(1), savedOwner.getId());
    }


    @Test
    void saveWithId() {
        owner.setId(1L);

        Owner savedOwner = testee.save(owner);

        assertEquals(new Long(1), savedOwner.getId());
    }

    @Test
    void delete() {
        Owner savedOwner = testee.save(owner);

        testee.delete(savedOwner);

        assertEquals(0, testee.findAll().size());
    }

    @Test
    void findById() {
        Owner savedOwner = testee.save(owner);

        Owner foundOwner = testee.findById(savedOwner.getId()).get();

        assertEquals(owner, foundOwner);
    }

    @Test
    void findByNonExistingId() {
        Optional<Owner> foundOwner = testee.findById(1L);

        assertTrue(!foundOwner.isPresent());
    }

    @Test
    void findAll() {
        Owner savedOwner = testee.save(owner);

        assertEquals(1, testee.findAll().size());
    }
}