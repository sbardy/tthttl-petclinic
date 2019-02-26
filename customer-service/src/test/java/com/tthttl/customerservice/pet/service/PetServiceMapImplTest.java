package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.CustomerServiceTestsHelper;
import com.tthttl.customerservice.pet.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetServiceMapImplTest {

    private PetServiceMapImpl testee;

    Pet pet;

    Pet savedPet;

    @BeforeEach
    void setUp() {
        testee = new PetServiceMapImpl();
        pet = CustomerServiceTestsHelper.createDog();
        savedPet = testee.save(pet);
    }

    @Test
    void save() {
        pet.setId(2L);
        Pet secondPet = testee.save(pet);

        assertEquals(new Long(2), secondPet.getId());
    }

    @Test
    void saveWithId() {
        Pet secondPet = testee.save(pet);

        assertEquals(new Long(1), secondPet.getId());
    }

    @Test
    void findById() {
        Pet foundPet = testee.findById(savedPet.getId()).get();

        assertEquals(foundPet, savedPet);
    }

    @Test
    void findAll() {
        assertEquals(1, testee.findAll().size());
    }

    @Test
    void delete() {
        testee.delete(savedPet);

        assertEquals(0, testee.findAll().size());
    }
}