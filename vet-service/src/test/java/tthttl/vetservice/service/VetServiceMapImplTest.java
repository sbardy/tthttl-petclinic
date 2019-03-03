package tthttl.vetservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tthttl.vetservice.model.Vet;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tthttl.vetservice.VetServiceTestHelper.createVet;

class VetServiceMapImplTest {

    private VetServiceMapImpl testee;
    Vet savedVet;

    @BeforeEach
    void setUp() {
        testee = new VetServiceMapImpl();
        savedVet = testee.save(createVet());
    }

    @Test
    void save() {
        Vet secondVet = testee.save(createVet());

        assertEquals(new Long(2), secondVet.getId());
    }

    @Test
    void saveWithId() {
        String updatedFirstName = "Morty";
        savedVet.setFirstName(updatedFirstName);
        Vet updatedVet = testee.save(savedVet);

        assertEquals(new Long(1), updatedVet.getId());
        assertEquals(updatedFirstName, updatedVet.getFirstName());
    }

    @Test
    void findById() {
        Vet foundVet = testee.findById(savedVet.getId()).get();

        assertEquals(savedVet, foundVet);
    }

    @Test
    void findAll() {
        List<Vet> pets = testee.findAll();

        assertEquals(Collections.singletonList(savedVet), pets);
    }

    @Test
    void delete() {
        testee.delete(savedVet);

        assertEquals(0, testee.findAll().size());
    }
}