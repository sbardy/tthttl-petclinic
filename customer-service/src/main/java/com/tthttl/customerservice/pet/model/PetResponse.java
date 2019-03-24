package com.tthttl.customerservice.pet.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PetResponse {

    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private PetType type;

    private String owner;

    public static PetResponse map(Pet petToCopy) {
        PetResponse petResponse = new PetResponse();
        petResponse.id = petToCopy.getId();
        petResponse.birthDate = petToCopy.getBirthDate();
        petResponse.name = petToCopy.getName();
        petResponse.owner = petToCopy.getOwner().getFirstName() + " " + petToCopy.getOwner().getLastName();
        petResponse.type = petToCopy.getType();
        return petResponse;
    }


}
