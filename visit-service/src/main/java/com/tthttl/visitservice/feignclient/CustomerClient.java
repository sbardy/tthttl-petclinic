package com.tthttl.visitservice.feignclient;

import com.tthttl.visitservice.model.PetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service", fallback = CustomerClientFallBack.class)
@Primary
public interface CustomerClient {

    @GetMapping("/pets/{id}")
    PetResponse getPet(@PathVariable Long id);

    @GetMapping("/pets")
    List<PetResponse> findAllPets();

}


