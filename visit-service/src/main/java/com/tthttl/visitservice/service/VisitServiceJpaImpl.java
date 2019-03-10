package com.tthttl.visitservice.service;

import com.tthttl.visitservice.model.Visit;
import com.tthttl.visitservice.repository.VisitRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile({"docker", "h2", "dev", "localhost"})
@Service
public class VisitServiceJpaImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceJpaImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }
}
