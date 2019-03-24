package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.pet.model.PetType;
import com.tthttl.customerservice.pet.repository.PetTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.tthttl.customerservice.CustomerServiceTestsHelper.createPetTypes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeServiceJpaImplTest {

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private PetTypeServiceJpaImpl testee;

    @Test
    void findAll() {
        List<PetType> petTypes = createPetTypes();
        when(petTypeRepository.findAll()).thenReturn(petTypes);

        List<PetType> foundPetTypes = testee.findAll();

        assertEquals(2, foundPetTypes.size());
        verify(petTypeRepository).findAll();
    }
}