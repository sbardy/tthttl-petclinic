package com.tthttl.customerservice.pet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.owner.service.OwnerService;
import com.tthttl.customerservice.pet.model.Pet;
import com.tthttl.customerservice.pet.service.PetService;
import com.tthttl.customerservice.pet.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static com.tthttl.customerservice.CustomerServiceTestsHelper.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class PetControllerTest {

    @Mock
    private PetService petService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetTypeService petTypeService;

    @InjectMocks
    private PetController testee;

    private Pet pet;
    private Owner owner;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        pet = createDog();
        owner = createOwner();
        mockMvc = MockMvcBuilders.standaloneSetup(testee).build();
    }

    @Test
    void save() throws Exception {
        when(ownerService.findById(any())).thenReturn(Optional.of(owner));
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(
                post("/pets/owner/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(createRequestBody(pet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(pet.getName())));
    }


    @Test
    void findById() throws Exception {
        pet.setOwner(owner);
        when(petService.findById(any())).thenReturn(Optional.of(pet));

        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(pet.getName())))
                .andExpect(jsonPath("$.owner", is(owner.getFirstName() + " " + owner.getLastName())));
    }

    @Test
    void findAll() throws Exception {
        pet.setOwner(owner);
        when(petService.findAll()).thenReturn(singletonList(pet));

        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(pet.getName())))
                .andExpect(jsonPath("$[0].owner", is(owner.getFirstName() + " " + owner.getLastName())));
    }

    @Test
    void delete() throws Exception {
        ArgumentCaptor<Pet> argumentCaptor = ArgumentCaptor.forClass(Pet.class);
        when(petService.findById(any())).thenReturn(Optional.of(pet));

        mockMvc.perform(MockMvcRequestBuilders.delete("/pets/1"))
                .andExpect(status().isNoContent());

        verify(petService, times(1)).delete(argumentCaptor.capture());
        assertEquals(pet, argumentCaptor.getValue());
    }

    @Test
    void update() throws Exception {
        Pet updatedPet = createDog();
        updatedPet.setName("Snuffles");
        updatedPet.setOwner(owner);
        when(petService.findById(ID)).thenReturn(Optional.of(pet));
        when(petService.save(any())).thenReturn(updatedPet);

        mockMvc.perform(
                put("/pets/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(createRequestBody(updatedPet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(updatedPet.getName())))
                .andExpect(jsonPath("$.owner", is(owner.getFirstName() + " " + owner.getLastName())));
    }

    @Test
    void findAllPetTypes() throws Exception {
        when(petTypeService.findAll()).thenReturn(createPetTypes());

        mockMvc.perform(get("/pets/pet-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    private String createRequestBody(Pet pet) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(pet);
    }
}
