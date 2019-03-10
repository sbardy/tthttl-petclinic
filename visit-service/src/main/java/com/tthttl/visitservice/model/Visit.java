package com.tthttl.visitservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "visits")
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String description;

    private Long petId;

    public static Visit update(Visit visitToUpdate, Visit visitToCopy) {
        visitToUpdate.setDate(visitToCopy.getDate());
        visitToUpdate.setDescription(visitToCopy.getDescription());
        visitToUpdate.setPetId(visitToCopy.getPetId());
        return visitToUpdate;
    }
}
