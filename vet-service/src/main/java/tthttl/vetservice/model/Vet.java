package tthttl.vetservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "specialties")
@EqualsAndHashCode(exclude = "specialties")
@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "vets_specialties",
            joinColumns = @JoinColumn(name = "vets_id"),
            inverseJoinColumns = @JoinColumn(name = "specialties_id"))
    private Set<Specialty> specialties = new HashSet<>();

    public static Vet update(Vet vetToUpdate, Vet vetToCopy) {
        vetToUpdate.setFirstName(vetToCopy.getFirstName());
        vetToUpdate.setLastName(vetToCopy.getLastName());
        vetToUpdate.setSpecialties(new HashSet<>(vetToCopy.getSpecialties()));
        return vetToUpdate;
    }

}
