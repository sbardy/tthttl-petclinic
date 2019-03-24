package tthttl.vetservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tthttl.vetservice.model.Specialty;
import tthttl.vetservice.repository.SpecialtyRepository;

import java.util.List;

@Profile({"docker", "h2", "dev", "localhost"})
@Service
public class SpecialtyServiceJpaImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceJpaImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public List<Specialty> findAll() {
        return this.specialtyRepository.findAll();
    }
}
