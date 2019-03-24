package com.tthttl.visitservice.controller;

import com.tthttl.visitservice.exception.ResourceNotFoundException;
import com.tthttl.visitservice.feignclient.CustomerClient;
import com.tthttl.visitservice.model.PetResponse;
import com.tthttl.visitservice.model.Visit;
import com.tthttl.visitservice.model.VisitResponse;
import com.tthttl.visitservice.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;
    private final CustomerClient customerClient;

    public VisitController(VisitService visitService, CustomerClient customerClient) {
        this.visitService = visitService;
        this.customerClient = customerClient;
    }

    @PostMapping
    public ResponseEntity<Visit> save(@RequestBody Visit visit) {
        Visit savedVisit = visitService.save(visit);
        URI location = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .buildAndExpand(savedVisit.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedVisit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> findById(@PathVariable Long id) {
        return visitService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> createResourceNotFoundException(String.valueOf(id)));
    }

    @GetMapping
    public ResponseEntity<List<Visit>> findAll() {
        return ResponseEntity.ok(visitService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visit> update(
            @PathVariable Long id,
            @RequestBody Visit visitToCopy) {
        return visitService.findById(id)
                .map(visitToUpdate -> {
                    Visit updatedVisit = visitService.save(Visit.update(visitToUpdate, visitToCopy));
                    URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
                    return ResponseEntity.created(location).body(updatedVisit);
                })
                .orElseThrow(() -> createResourceNotFoundException(String.valueOf(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return visitService.findById(id)
                .map(foundVisit -> {
                    visitService.delete(foundVisit);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> createResourceNotFoundException(String.valueOf(id)));
    }

    @GetMapping("/{id}/with-pet")
    public ResponseEntity<VisitResponse> getVisitWithPet(@PathVariable Long id) {
        return visitService.findById(id)
                .map(foundVisit -> {
                    PetResponse petResponse = customerClient.getPet(foundVisit.getPetId());
                    return ResponseEntity.ok().body(new VisitResponse(foundVisit, petResponse));
                })
                .orElseThrow(() -> createResourceNotFoundException(String.valueOf(id)));
    }

    @GetMapping("/with-pet")
    public ResponseEntity<List<VisitResponse>> getVisitsWithPet() {
        List<Visit> visits = visitService.findAll();
        List<PetResponse> pets = customerClient.findAllPets();
        List<VisitResponse> visitResponses = new ArrayList<>();
        visits.forEach(visit -> {
            pets.stream()
                    .filter(pet -> pet.getId().equals(visit.getPetId()))
                    .forEach(pet -> visitResponses.add(new VisitResponse(visit, pet)));
        });
        return ResponseEntity.ok().body(visitResponses);
    }

    //TODO implement with tests
    @GetMapping("/pets/{id}")
    public ResponseEntity<List<VisitResponse>> findVisitByPetId(@PathVariable Long id) {
        return null;
    }

    private ResourceNotFoundException createResourceNotFoundException(String message) {
        return new ResourceNotFoundException(message, getClass().toString());
    }

}
