package tthttl.vetservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "specialties")
@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
