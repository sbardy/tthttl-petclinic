package tthttl.vetservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tthttl.vetservice.model.Vet;

import java.util.*;

@Profile("map")
@Service
public class VetServiceMapImpl implements VetService {

    Map<Long, Vet> vetDb = new HashMap<>();

    @Override
    public Vet save(Vet vet) {
        if (vet.getId() == null) {
            vet.setId(vetDb.isEmpty() ? 1L : Collections.max(vetDb.keySet()) + 1);
        }
        vetDb.put(vet.getId(), vet);
        return vet;
    }

    @Override
    public Optional<Vet> findById(Long id) {
        Vet foundPet = vetDb.get(id);
        if (foundPet == null) {
            return Optional.empty();
        }
        return Optional.of(foundPet);
    }

    @Override
    public List<Vet> findAll() {
        return new ArrayList<>(vetDb.values());
    }

    @Override
    public void delete(Vet vet) {
        vetDb.remove(vet.getId());
    }
}
