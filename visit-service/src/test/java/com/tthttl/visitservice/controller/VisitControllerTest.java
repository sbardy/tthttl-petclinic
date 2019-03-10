package com.tthttl.visitservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tthttl.visitservice.feignclient.CustomerClient;
import com.tthttl.visitservice.model.PetResponse;
import com.tthttl.visitservice.model.Visit;
import com.tthttl.visitservice.service.VisitService;
import net.minidev.json.JSONArray;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static com.tthttl.visitservice.VisitServiceTestHelper.createVisit;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    private VisitService visitService;

    @Mock
    private CustomerClient customerClient;

    @InjectMocks
    private VisitController testee;

    private MockMvc mockMvc;

    private Visit visit;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testee).build();
        visit = createVisit();
    }

    @Test
    void save() throws Exception {
        when(visitService.save(any())).thenReturn(visit);

        mockMvc.perform(
                post("/visits")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(createRequestBody(visit))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.date", is(createJsonArrayFromDate(visit.getDate()))))
                .andExpect(jsonPath("$.description", is(visit.getDescription())))
                .andExpect(jsonPath("$.petId", is(visit.getPetId().intValue())));
    }

    @Test
    void findById() throws Exception {
        when(visitService.findById(any())).thenReturn(Optional.of(visit));

        mockMvc.perform(get("/visits/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(createJsonArrayFromDate(visit.getDate()))))
                .andExpect(jsonPath("$.description", is(visit.getDescription())))
                .andExpect(jsonPath("$.petId", is(visit.getPetId().intValue())));
    }

    @Test
    void getVisitWithPet() throws Exception {
        when(visitService.findById(any())).thenReturn(Optional.of(visit));
        PetResponse petResponse = new PetResponse();
        petResponse.setName("Snowball");
        when(customerClient.getPet(any())).thenReturn(petResponse);

        mockMvc.perform(get("/visits/1/with-pet"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(createJsonArrayFromDate(visit.getDate()))))
                .andExpect(jsonPath("$.description", is(visit.getDescription())))
                .andExpect(jsonPath("$.pet.name", is(petResponse.getName())));
    }

    @Test
    void findAll() throws Exception {
        when(visitService.findAll()).thenReturn(Collections.singletonList(visit));

        mockMvc.perform(get("/visits"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", is(visit.getDescription())));
    }

    @Test
    void update() throws Exception {
        when(visitService.findById(any())).thenReturn(Optional.of(visit));
        String updatedDescription = "UPDATED";
        visit.setDescription(updatedDescription);
        when(visitService.save(any())).thenReturn(visit);

        mockMvc.perform(put("/visits/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(createRequestBody(visit))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", is(updatedDescription)));
    }

    @Test
    void delete() throws Exception {
        when(visitService.findById(any())).thenReturn(Optional.of(visit));
        ArgumentCaptor<Visit> argumentCaptor = ArgumentCaptor.forClass(Visit.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/visits/1"))
                .andExpect(status().isNoContent());

        verify(visitService, times(1)).delete(argumentCaptor.capture());
        assertEquals(visit, argumentCaptor.getValue());
    }

    private String createRequestBody(Visit visit) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(visit);
    }

    private JSONArray createJsonArrayFromDate(LocalDate date) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.appendElement(date.getYear());
        jsonArray.appendElement(date.getMonthValue());
        jsonArray.appendElement(date.getDayOfMonth());
        return jsonArray;
    }
}