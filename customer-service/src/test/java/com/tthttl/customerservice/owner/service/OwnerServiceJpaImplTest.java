package com.tthttl.customerservice.owner.service;

import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.owner.repository.OwnerRepository;
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
import static com.tthttl.customerservice.CustomerServiceTestsHelper.createOwner;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJpaImplTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceJpaImpl testee;

    private Owner owner = createOwner();
    private ArgumentCaptor<Owner> argumentCaptor;

    @BeforeEach
    void setUp() {
        argumentCaptor = ArgumentCaptor.forClass(Owner.class);
    }

    @Test
    void save() {
        //given
        when(ownerRepository.save(any())).thenReturn(owner);

        //when
        Owner returnedOwner = testee.save(owner);

        //then
        verify(ownerRepository, times(1)).save(argumentCaptor.capture());
        assertEquals(owner, argumentCaptor.getValue());
        assertEquals(returnedOwner, owner);
    }

    @Test
    void delete() {
        testee.delete(owner);

        verify(ownerRepository, times(1)).delete(argumentCaptor.capture());
        assertEquals(owner, argumentCaptor.getValue());
    }

    @Test
    void findById() {
        //given
        when(ownerRepository.findById(any())).thenReturn(Optional.of(owner));
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        //when
        Optional<Owner> foundOwner = testee.findById(ID);

        //then
        verify(ownerRepository, times(1)).findById(idCaptor.capture());
        assertEquals(ID, idCaptor.getValue());
        assertEquals(foundOwner.get(), owner);
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(singletonList(owner));

        List<Owner> owners = testee.findAll();

        verify(ownerRepository, times(1)).findAll();
        assertEquals(1, owners.size());
        assertEquals(owner, owners.get(0));
    }

}