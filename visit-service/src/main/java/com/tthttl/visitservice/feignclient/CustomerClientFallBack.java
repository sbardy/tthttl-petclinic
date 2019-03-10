package com.tthttl.visitservice.feignclient;

import com.tthttl.visitservice.model.PetResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerClientFallBack implements CustomerClient {

    @Override
    public PetResponse getPet(Long id) {
        return new PetResponse();
    }
}