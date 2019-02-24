package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.pet.model.Pet;
import com.tthttl.customerservice.pet.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.tthttl.customerservice.CustomerServiceTestsHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("h2")
@DataJpaTest
class PetServiceJpaIT {

    @Autowired
    private PetRepository petRepository;

    private PetServiceJpaImpl testee;

    @BeforeEach
    void setUp() {
        testee = new PetServiceJpaImpl(petRepository);
    }

    @Test
    void save() {
        Pet savedDog = testee.save(createDog());

        Optional<Pet> foundDog = testee.findById(savedDog.getId());
        assertEquals(savedDog, foundDog.get());
    }

    @Test
    void findById() {
        assertEquals(ID, testee.findById(ID).get().getId());
    }

    @Test
    void findAll() {
        assertEquals(DATA_SQL_PETS_SIZE, testee.findAll().size());
    }

    @Test
    @DirtiesContext
    void delete() {
        Pet petToDelete = testee.findById(ID).get();
        testee.delete(petToDelete);
        assertThrows(NoSuchElementException.class, () -> testee.findAll().stream()
                .filter(pet -> pet.getId().equals(ID))
                .findFirst()
                .get());

    }
}