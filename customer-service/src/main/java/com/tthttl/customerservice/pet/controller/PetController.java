package com.tthttl.customerservice.pet.controller;

import com.tthttl.customerservice.exception.ResourceNotFoundException;
import com.tthttl.customerservice.pet.model.Pet;
import com.tthttl.customerservice.pet.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/pets")
@RestController
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    //TODO refactor => save pet for user
    @PostMapping
    public ResponseEntity<Pet> save(@RequestBody Pet pet) {
        Pet savedPet = petService.save(pet);
        URI location = MvcUriComponentsBuilder
                .fromController(getClass())
                .path("/{id}")
                .buildAndExpand(savedPet.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedPet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findById(@PathVariable Long id) {
        return petService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> createPetNotFoundException(id));
    }

    @GetMapping
    public ResponseEntity<List<Pet>> findAll() {
        return ResponseEntity.ok(petService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return petService.findById(id)
                .map(petToDelete -> {
                    petService.delete(petToDelete);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> createPetNotFoundException(id));
    }

    @PutMapping
    public ResponseEntity<Pet> update(@PathVariable Long id,
                                      @RequestBody Pet petToCopy) {
        return petService.findById(id)
                .map(petToUpdate -> {
                    Pet updatedPet = petService.save(Pet.update(petToUpdate, petToCopy));
                    URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
                    return ResponseEntity.created(location).body(updatedPet);
                })
                .orElseThrow(() -> createPetNotFoundException(id));
    }

    private ResourceNotFoundException createPetNotFoundException(Long id) {
        return new ResourceNotFoundException(String.valueOf(id), getClass().toString());
    }


}
