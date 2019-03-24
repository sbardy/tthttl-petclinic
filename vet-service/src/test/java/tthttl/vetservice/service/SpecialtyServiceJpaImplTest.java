package tthttl.vetservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tthttl.vetservice.model.Specialty;
import tthttl.vetservice.repository.SpecialtyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SpecialtyServiceJpaImplTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialtyServiceJpaImpl testee;

    @Test
    void findAll() {
        List<Specialty> specialties = new ArrayList<>(Arrays.asList(new Specialty(1L, "internal medicine")));
        Mockito.when(specialtyRepository.findAll()).thenReturn(specialties);

        List<Specialty> foundSpecialties = testee.findAll();
        assertEquals(specialties, foundSpecialties);
    }
}