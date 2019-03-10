package com.tthttl.visitservice.service;

import com.tthttl.visitservice.model.Visit;
import com.tthttl.visitservice.repository.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.tthttl.visitservice.VisitServiceTestHelper.ID;
import static com.tthttl.visitservice.VisitServiceTestHelper.createVisit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitServiceJpaImplTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitServiceJpaImpl testee;

    private Visit visit;
    private ArgumentCaptor<Visit> argumentCaptor;


    @BeforeEach
    void setUp() {
        visit = createVisit();
        argumentCaptor = ArgumentCaptor.forClass(Visit.class);
    }

    @Test
    void save() {
        when(visitRepository.save(any())).thenReturn(visit);

        Visit savedVisit = testee.save(visit);

        assertEquals(savedVisit, visit);
        verify(visitRepository, times(1)).save(argumentCaptor.capture());
        assertEquals(visit, argumentCaptor.getValue());
    }

    @Test
    void findById() {
        when(visitRepository.findById(ID)).thenReturn(Optional.of(visit));

        Visit foundVisit = testee.findById(ID).get();

        assertEquals(visit, foundVisit);
    }

    @Test
    void findAll() {
        when(visitRepository.findAll()).thenReturn(Collections.singletonList(visit));

        List<Visit> visits = testee.findAll();

        assertEquals(1, visits.size());
        assertEquals(visit, visits.get(0));
    }

    @Test
    void delete() {
        testee.delete(visit);

        verify(visitRepository, times(1)).delete(argumentCaptor.capture());
        assertEquals(visit, argumentCaptor.getValue());
    }
}