package com.tthttl.visitservice.service;

import com.tthttl.visitservice.model.Visit;
import com.tthttl.visitservice.repository.VisitRepository;
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

import static com.tthttl.visitservice.VisitServiceTestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("h2")
class VisitServiceJpaImpIT {

    @Autowired
    private VisitRepository visitRepository;

    private VisitServiceJpaImpl testee;

    private Visit visit;

    @BeforeEach
    void setUp() {
        testee = new VisitServiceJpaImpl(visitRepository);
        visit = createVisit();
    }

    @Test
    @DirtiesContext
    void save() {
        Visit savedVisit = testee.save(visit);

        assertEquals(new Long(DATA_SQL_VISITS_SIZE + 1), savedVisit.getId());
    }

    @Test
    @DirtiesContext
    void saveWithId() {
        visit.setId(1L);
        Visit savedVisit = testee.save(visit);

        assertEquals(new Long(1), savedVisit.getId());
        assertEquals(visit, savedVisit);
    }

    @Test
    void findById() {
        Visit foundVisit = testee.findById(ID).get();

        assertEquals(ID, foundVisit.getId());
    }

    @Test
    void findAll() {
        List<Visit> visits = testee.findAll();

        assertEquals(DATA_SQL_VISITS_SIZE, visits.size());
    }

    @Test
    @DirtiesContext
    void delete() {
        visit.setId(ID);

        testee.delete(visit);

        assertThrows(NoSuchElementException.class, () -> testee.findAll().stream()
                .filter(visit -> visit.getId().equals(ID)).findFirst()
                .get());
    }
}