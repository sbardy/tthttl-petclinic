package tthttl.vetservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tthttl.vetservice.model.Vet;
import tthttl.vetservice.repository.VetRepository;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static tthttl.vetservice.VetServiceTestHelper.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("h2")
@DataJpaTest
class VetServiceJpaImpIT {

    @Autowired
    private VetRepository vetRepository;

    private VetServiceJpaImpl testee;

    private Vet vet;

    @BeforeEach
    void setUp() {
        testee = new VetServiceJpaImpl(vetRepository);
        vet = createVet();
    }

    @Test
    void save() {
        Vet savedPet = testee.save(vet);

        assertEquals(vet, savedPet);
    }

    @Test
    void saveWithId() {
        String updatedFirstName = "Morty";
        Vet foundVet = testee.findById(ID).get();
        foundVet.setFirstName(updatedFirstName);

        Vet savedPet = testee.save(foundVet);

        assertEquals(updatedFirstName, savedPet.getFirstName());
    }

    @Test
    void findById() {
        Vet foundVet = testee.findById(ID).get();

        assertEquals(foundVet.getId(), ID);
    }

    @Test
    void findAll() {
        assertEquals(VET_DATA_SQL_SIZE, testee.findAll().size());
    }

    @Test
    @DirtiesContext
    void delete() {
        Vet vetToDelete = testee.findById(ID).get();

        testee.delete(vetToDelete);

        assertThrows(NoSuchElementException.class, () -> testee.findAll().stream()
                .filter(pet -> pet.getId().equals(vetToDelete.getId()))
                .findFirst()
                .get());
    }

}