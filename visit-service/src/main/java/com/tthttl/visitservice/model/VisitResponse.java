package com.tthttl.visitservice.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitResponse {

    private Long id;

    private LocalDate date;

    private String description;

    private PetResponse pet;

    public VisitResponse(Visit visit, PetResponse petResponse) {
        this.id = visit.getId();
        this.date = visit.getDate();
        this.description = visit.getDescription();
        this.pet = petResponse;
    }

}
