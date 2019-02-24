package com.tthttl.customerservice.owner.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tthttl.customerservice.CustomerServiceTestsHelper;
import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.owner.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static com.tthttl.customerservice.CustomerServiceTestsHelper.ID;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController testee;

    private MockMvc mockMvc;

    private Owner owner;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testee).build();
        owner = CustomerServiceTestsHelper.createOwner();
    }

    @Test
    void createOwner() throws Exception {
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(
                post("/owners")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(createRequestBody(owner)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(owner.getFirstName())));
    }

    @Test
    void getOwner() throws Exception {
        when(ownerService.findById(any())).thenReturn(Optional.of(owner));

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(owner.getFirstName())));
    }

    @Test
    void findAll() throws Exception {
        when(ownerService.findAll()).thenReturn(singletonList(owner));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(owner.getFirstName())));
    }

    @Test
    void deleteOwner() throws Exception {
        ArgumentCaptor<Owner> argumentCaptor = ArgumentCaptor.forClass(Owner.class);
        when(ownerService.findById(ID)).thenReturn(Optional.of(owner));

        mockMvc.perform(delete("/owners/1"))
                .andExpect(status().is(204));

        verify(ownerService, times(1)).delete(argumentCaptor.capture());
        assertEquals(owner, argumentCaptor.getValue());
    }

    @Test
    void updateOwner() throws Exception {
        Owner updatedOwner = CustomerServiceTestsHelper.createOwner();
        updatedOwner.setFirstName("Rick");
        when(ownerService.findById(ID)).thenReturn(Optional.of(owner));
        when(ownerService.save(owner)).thenReturn(updatedOwner);

        mockMvc.perform(
                put("/owners/1")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(createRequestBody(updatedOwner)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(updatedOwner.getFirstName())));
    }

    private String createRequestBody(Owner owner) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(owner);
    }
}