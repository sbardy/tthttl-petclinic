package tthttl.vetservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tthttl.vetservice.model.Specialty;
import tthttl.vetservice.model.Vet;
import tthttl.vetservice.service.SpecialtyService;
import tthttl.vetservice.service.VetService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tthttl.vetservice.VetServiceTestHelper.createSpecialty;
import static tthttl.vetservice.VetServiceTestHelper.createVet;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private VetService vetService;

    @Mock
    private SpecialtyService specialtyService;

    @InjectMocks
    private VetController testee;

    private MockMvc mockMvc;

    private Vet vet;
    private Specialty savedSpecialty;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testee).build();
        vet = createVet();
        savedSpecialty = createSpecialty();
    }

    @Test
    void saveVet() throws Exception {
        when(specialtyService.findAll()).thenReturn(Collections.singletonList(savedSpecialty));
        when(vetService.save(any())).thenReturn(vet);

        mockMvc.perform
                (post("/vets")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(createRequestBody(vet))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(vet.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(vet.getLastName())))
                .andExpect(jsonPath("$.specialties", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.specialties[0].id", is(vet.getSpecialties()
                        .iterator().next().getId().intValue())))
                .andExpect(jsonPath("$.specialties[0].name", is(vet.getSpecialties().iterator().next().getName())));
    }

    @Test
    void saveVetWithExistingAndNewSpecialties() throws Exception {
        vet.setSpecialties(new HashSet<>(Arrays.asList(savedSpecialty, new Specialty(null, "newSpecialty"))));
        when(specialtyService.findAll()).thenReturn(Collections.singletonList(savedSpecialty));
        when(vetService.save(any())).thenReturn(vet);

        mockMvc.perform
                (post("/vets")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(createRequestBody(vet))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(vet.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(vet.getLastName())))
                .andExpect(jsonPath("$.specialties", Matchers.hasSize(2)));
    }

    @Test
    void findById() throws Exception {
        when(vetService.findById(any())).thenReturn(Optional.of(vet));

        mockMvc.perform(get("/vets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(vet.getFirstName())));

    }

    @Test
    void findAll() throws Exception {
        when(vetService.findAll()).thenReturn(Collections.singletonList(vet));

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(vet.getFirstName())));
    }

    @Test
    void update() throws Exception {
        Vet updatedVet = new Vet();
        String updatedName = "Morty";
        updatedVet.setFirstName(updatedName);
        when(vetService.findById(any())).thenReturn(Optional.of(vet));
        when(vetService.save(any())).thenReturn(updatedVet);

        mockMvc.perform(
                put("/vets/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(createRequestBody(vet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(updatedName)));
    }

    @Test
    void delete() throws Exception {
        when(vetService.findById(any())).thenReturn(Optional.of(vet));
        ArgumentCaptor<Vet> argumentCaptor = ArgumentCaptor.forClass(Vet.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/vets/1"))
                .andExpect(status().isNoContent());
        verify(vetService, times(1)).delete(argumentCaptor.capture());
        assertEquals(vet, argumentCaptor.getValue());
    }

    @Test
    public void findAllSpecialties() throws Exception {
        String specialtyName = "internal medicine";
        when(specialtyService.findAll())
                .thenReturn(Collections.singletonList(new Specialty(1L, specialtyName)));
        mockMvc.perform(get("/vets/specialties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(specialtyName)));
    }

    private String createRequestBody(Vet vet) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(vet);
    }

}