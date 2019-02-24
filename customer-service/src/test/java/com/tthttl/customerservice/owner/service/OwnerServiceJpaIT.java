package com.tthttl.customerservice.owner.service;

import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.owner.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.tthttl.customerservice.CustomerServiceTestsHelper.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("h2")
class OwnerServiceJpaIT {

    @Autowired
    private OwnerRepository ownerRepository;

    private OwnerServiceJpaImpl testee;

    @BeforeEach
    void setUp() {
        testee = new OwnerServiceJpaImpl(ownerRepository);
    }

    @Test
    void save() {
        Owner secondOwner = testee.save(createOwner());

        assertNotNull(secondOwner);
        Optional<Owner> foundOwner = testee.findById(secondOwner.getId());
        assertEquals(secondOwner, foundOwner.get());
    }

    @Test
    @DirtiesContext
    void delete() {
        Optional<Owner> ownerToDelete = testee.findById(ID);
        testee.delete(ownerToDelete.get());

        assertThrows(NoSuchElementException.class, () -> testee.findAll().stream()
                .filter(owner -> owner.getId().equals(ownerToDelete.get().getId()))
                .findFirst()
                .get());
    }

    @Test
    void findById() {
        Optional<Owner> foundOwner = testee.findById(ID);

        assertEquals(ID, foundOwner.get().getId());
    }

    @Test
    void findAll() {
        List<Owner> ownerList = testee.findAll();

        assertEquals(DATA_SQL_OWNERS_SIZE, ownerList.size());
    }
}