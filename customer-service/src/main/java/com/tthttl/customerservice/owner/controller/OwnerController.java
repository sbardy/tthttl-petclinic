package com.tthttl.customerservice.owner.controller;

import com.tthttl.customerservice.exception.ResourceNotFoundException;
import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.owner.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@Valid @RequestBody Owner owner) {
        Owner savedOwner = ownerService.save(owner);
        URI location = MvcUriComponentsBuilder
                .fromController(getClass())
                .path("/{id}")
                .buildAndExpand(savedOwner.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedOwner);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        return ownerService.findById(id)
                .map(owner -> ResponseEntity.ok().body(owner))
                .orElseThrow(() -> createResourceNotFoundException(id));
    }

    @GetMapping
    public ResponseEntity<List<Owner>> findAll() {
        return ResponseEntity.ok().body(ownerService.findAll());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long id) {
        return ownerService.findById(id)
                .map(owner -> {
                    ownerService.delete(owner);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> createResourceNotFoundException(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id,
                                             @Valid @RequestBody Owner ownerToCopy) {
        return ownerService.findById(id)
                .map(ownerToUpdate -> {
                    Owner updatedOwner = ownerService.save(Owner.update(ownerToUpdate, ownerToCopy));
                    URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
                    return ResponseEntity.created(location).body(updatedOwner);
                })
                .orElseThrow(() -> createResourceNotFoundException(id));
    }

    private ResourceNotFoundException createResourceNotFoundException(Long id){
        return new ResourceNotFoundException(String.valueOf(id), getClass().toString());
    }


}
