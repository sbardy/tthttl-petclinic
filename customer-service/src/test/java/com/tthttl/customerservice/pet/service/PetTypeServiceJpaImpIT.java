package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.pet.model.PetType;
import com.tthttl.customerservice.pet.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.tthttl.customerservice.CustomerServiceTestsHelper.DATA_SQL_PET_TYPE_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("h2")
@DataJpaTest
class PetTypeServiceJpaImpIT {

    @Autowired
    private PetTypeRepository petTypeRepository;

    private PetTypeServiceJpaImpl testee;

    @BeforeEach
    void setUp() {
        testee = new PetTypeServiceJpaImpl(petTypeRepository);
    }

    @Test
    void findAll() {
        List<PetType> foundPetTypes = testee.findAll();

        assertEquals(DATA_SQL_PET_TYPE_SIZE, foundPetTypes.size());
        assertThat(foundPetTypes.stream().map(PetType::getName))
                .containsExactlyInAnyOrder("cat", "dog", "lizard", "snake", "bird", "hamster");
    }
}