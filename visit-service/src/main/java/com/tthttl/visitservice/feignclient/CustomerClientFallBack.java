package com.tthttl.visitservice.feignclient;

import com.tthttl.visitservice.model.PetResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CustomerClientFallBack implements CustomerClient {

    @Override
    public PetResponse getPet(Long id) {
        return new PetResponse();
    }

    @Override
    public List<PetResponse> findAllPets() {
        return Collections.emptyList();
    }
}