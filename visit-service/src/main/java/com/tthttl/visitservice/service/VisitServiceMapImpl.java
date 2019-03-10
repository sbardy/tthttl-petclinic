package com.tthttl.visitservice.service;

import com.tthttl.visitservice.model.Visit;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile("map")
@Service
public class VisitServiceMapImpl implements VisitService {

    private Map<Long, Visit> map = new HashMap<>();

    @Override
    public Visit save(Visit visit) {
        if (visit.getId() == null) {
            visit.setId(map.keySet().isEmpty() ? 1L : Collections.max(map.keySet()) + 1);
        }
        map.put(visit.getId(), visit);
        return visit;
    }

    @Override
    public Optional<Visit> findById(Long id) {
        Visit foundVisit = map.get(id);
        return foundVisit == null ? Optional.empty() : Optional.of(foundVisit);
    }

    @Override
    public List<Visit> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void delete(Visit visit) {
        map.remove(visit.getId());
    }
}
