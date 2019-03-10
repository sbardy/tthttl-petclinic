package com.tthttl.visitservice.service;

import com.tthttl.visitservice.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tthttl.visitservice.VisitServiceTestHelper.ID;
import static com.tthttl.visitservice.VisitServiceTestHelper.createVisit;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitServiceMapImplTest {

    private VisitServiceMapImpl testee;

    private Visit savedVisit;

    @BeforeEach
    void setUp() {
        testee = new VisitServiceMapImpl();
        savedVisit = testee.save(createVisit());
    }

    @Test
    void save() {
        assertEquals(ID, savedVisit.getId());
    }

    @Test
    void findById() {
        Visit foundVisit = testee.findById(ID).get();

        assertEquals(ID, foundVisit.getId());
    }

    @Test
    void findAll() {
        assertEquals(1, testee.findAll().size());
    }

    @Test
    void delete() {
        testee.delete(savedVisit);
        assertEquals(0, testee.findAll().size());
    }
}