package tthttl.vetservice.service;

import tthttl.vetservice.model.Vet;

import java.util.List;
import java.util.Optional;

public interface VetService {

    Vet save(Vet vet);
    Optional<Vet> findById(Long id);
    List<Vet>findAll();
    void delete(Vet vet);


}
