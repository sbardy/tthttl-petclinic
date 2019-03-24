package com.tthttl.customerservice.pet.controller;

import com.tthttl.customerservice.exception.ResourceNotFoundException;
import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.owner.service.OwnerService;
import com.tthttl.customerservice.pet.model.Pet;
import com.tthttl.customerservice.pet.model.PetResponse;
import com.tthttl.customerservice.pet.model.PetType;
import com.tthttl.customerservice.pet.service.PetService;
import com.tthttl.customerservice.pet.service.PetTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/pets")
@RestController
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @PostMapping("owner/{id}")
    public ResponseEntity<PetResponse> save(@PathVariable Long id,
                                            @RequestBody Pet pet) {
        Owner ownerForPet = ownerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(id), Owner.class.toString()));
        pet.setOwner(ownerForPet);
        ownerForPet.addPet(pet);
        Pet savedPet = ownerService.save(ownerForPet).getPets().stream()
                .filter(p -> p.getName().equals(pet.getName()) && p.getBirthDate().equals(pet.getBirthDate()))
                .findFirst()
                .get();
        PetResponse petResponse = PetResponse.map(savedPet);
        URI location = MvcUriComponentsBuilder
                .fromController(getClass())
                .path("/{id}")
                .buildAndExpand(petResponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(petResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> findById(@PathVariable Long id) {
        return petService.findById(id)
                .map(pet -> ResponseEntity.ok(PetResponse.map(pet)))
                .orElseThrow(() -> createPetNotFoundException(id));
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> findAll() {
        return ResponseEntity.ok(petService.findAll().stream()
                .map(PetResponse::map)
                .collect(Collectors.toList()));
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

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> update(@PathVariable Long id,
                                              @RequestBody Pet petToCopy) {
        return petService.findById(id)
                .map(petToUpdate -> {
                    Pet updatedPet = petService.save(Pet.update(petToUpdate, petToCopy));
                    URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
                    return ResponseEntity.created(location).body(PetResponse.map(updatedPet));
                })
                .orElseThrow(() -> createPetNotFoundException(id));
    }

    @GetMapping("/pet-types")
    public ResponseEntity<List<PetType>> findAllPetTypes(){
        return ResponseEntity.ok().body(petTypeService.findAll());
    }

    private ResourceNotFoundException createPetNotFoundException(Long id) {
        return new ResourceNotFoundException(String.valueOf(id), Pet.class.toString());
    }


}
