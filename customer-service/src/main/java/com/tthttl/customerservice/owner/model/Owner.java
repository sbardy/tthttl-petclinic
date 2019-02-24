package com.tthttl.customerservice.owner.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tthttl.customerservice.pet.model.Pet;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "pets")
@EqualsAndHashCode(exclude = "pets")
@Table(name = "owners")
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String address;

    @NotEmpty
    private String city;

    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @JsonManagedReference
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pet> pets = new HashSet<>();

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }

    public static Owner update(Owner ownerToUpdate, Owner ownerToCopy) {
        ownerToUpdate.firstName = ownerToCopy.firstName;
        ownerToUpdate.lastName = ownerToCopy.lastName;
        ownerToUpdate.address = ownerToCopy.address;
        ownerToUpdate.city = ownerToCopy.city;
        ownerToUpdate.telephone = ownerToCopy.telephone;
        return ownerToUpdate;
    }
}
