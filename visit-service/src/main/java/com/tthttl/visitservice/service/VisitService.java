package com.tthttl.visitservice.service;

import com.tthttl.visitservice.model.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitService {

    Visit save(Visit visit);

    Optional<Visit> findById(Long id);

    List<Visit> findAll();

    void delete(Visit visit);

}
