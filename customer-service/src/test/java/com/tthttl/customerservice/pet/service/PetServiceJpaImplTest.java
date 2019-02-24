package com.tthttl.customerservice.pet.service;

import com.tthttl.customerservice.CustomerServiceTestsHelper;
import com.tthttl.customerservice.pet.model.Pet;
import com.tthttl.customerservice.pet.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.tthttl.customerservice.CustomerServiceTestsHelper.ID;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceJpaImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceJpaImpl testee;

    private ArgumentCaptor<Pet> argumentCaptor = ArgumentCaptor.forClass(Pet.class);

    private Pet dog;

    @BeforeEach
    void setUp() {
        dog = CustomerServiceTestsHelper.createDog();
    }

    @Test
    void save() {
        when(petRepository.save(any())).thenReturn(dog);

        testee.save(dog);

        verify(petRepository, times(1)).save(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), dog);
    }

    @Test
    void findById() {
        when(petRepository.findById(any())).thenReturn(Optional.of(dog));

        Optional<Pet> foundPet = testee.findById(ID);

        verify(petRepository, times(1)).findById(ID);
        assertEquals(foundPet.get(), dog);
    }

    @Test
    void findAll() {
        when(petRepository.findAll()).thenReturn(singletonList(dog));

        List<Pet> pets = testee.findAll();

        verify(petRepository, times(1)).findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void delete() {
        testee.delete(dog);

        verify(petRepository, times(1)).delete(argumentCaptor.capture());
        assertEquals(dog, argumentCaptor.getValue());
    }
}