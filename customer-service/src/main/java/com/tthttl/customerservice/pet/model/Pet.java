package com.tthttl.customerservice.pet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tthttl.customerservice.owner.model.Owner;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = "owner")
@ToString(exclude = "owner")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "pet_type_id")
    private PetType type;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public static Pet update(Pet petToUpdate, Pet petToCopy) {
        petToUpdate.name = petToCopy.name;
        petToUpdate.birthDate = petToCopy.birthDate;
        petToUpdate.type = petToCopy.type;
        return petToUpdate;
    }

}
