package tthttl.vetservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tthttl.vetservice.model.Vet;
import tthttl.vetservice.repository.VetRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static tthttl.vetservice.VetServiceTestHelper.ID;
import static tthttl.vetservice.VetServiceTestHelper.createVet;

@ExtendWith(MockitoExtension.class)
class VetServiceJpaImplTest {

    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    private VetServiceJpaImpl testee;

    Vet vet;

    ArgumentCaptor<Vet> argumentCaptor;

    @BeforeEach
    void setUp() {
        vet = createVet();
        argumentCaptor = ArgumentCaptor.forClass(Vet.class);
    }

    @Test
    void save() {
        when(vetRepository.save(any())).thenReturn(vet);

        testee.save(vet);

        verify(vetRepository, times(1)).save(argumentCaptor.capture());
        assertEquals(vet, argumentCaptor.getValue());
    }

    @Test
    void findById() {
        when(vetRepository.findById(ID)).thenReturn(Optional.of(vet));

        Vet foundVet = testee.findById(ID).get();

        assertEquals(vet, foundVet);
    }

    @Test
    void findAll() {
        when(vetRepository.findAll()).thenReturn(Collections.singletonList(vet));

        List<Vet> vets = testee.findAll();

        assertEquals(1, vets.size());
        assertEquals(vet, vets.get(0));
    }

    @Test
    void delete() {
        testee.delete(vet);

        verify(vetRepository, times(1)).delete(argumentCaptor.capture());
        assertEquals(vet, argumentCaptor.getValue());
    }
}