package com.tthttl.customerservice.pet.model;

import com.tthttl.customerservice.owner.model.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = "owner")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType type;

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
