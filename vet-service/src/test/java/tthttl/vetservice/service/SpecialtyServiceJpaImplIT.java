package tthttl.vetservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tthttl.vetservice.model.Specialty;
import tthttl.vetservice.repository.SpecialtyRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tthttl.vetservice.VetServiceTestHelper.SPECIALTY_DATA_SQL_SIZE;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("h2")
@DataJpaTest
class SpecialtyServiceJpaImplIT {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    private SpecialtyServiceJpaImpl testee;

    @BeforeEach
    void setUp() {
        testee = new SpecialtyServiceJpaImpl(specialtyRepository);
    }

    @Test
    void findAll() {
        List<Specialty> foundSpecialties = testee.findAll();

        assertEquals(SPECIALTY_DATA_SQL_SIZE, foundSpecialties.size());
    }
}