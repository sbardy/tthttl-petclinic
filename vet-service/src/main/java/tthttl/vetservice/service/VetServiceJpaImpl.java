package tthttl.vetservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tthttl.vetservice.model.Vet;
import tthttl.vetservice.repository.VetRepository;

import java.util.List;
import java.util.Optional;

@Profile({"docker", "h2", "dev"})
@Service
public class VetServiceJpaImpl implements VetService {

    private final VetRepository vetRepository;

    public VetServiceJpaImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Optional<Vet> findById(Long id) {
        return vetRepository.findById(id);
    }

    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }
}
