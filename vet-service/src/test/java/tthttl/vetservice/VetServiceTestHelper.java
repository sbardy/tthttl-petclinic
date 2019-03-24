package tthttl.vetservice;

import tthttl.vetservice.model.Specialty;
import tthttl.vetservice.model.Vet;

import java.util.HashSet;
import java.util.Set;

public class VetServiceTestHelper {

    public static Long ID = 1L;
    public static int VET_DATA_SQL_SIZE = 6;
    public static int SPECIALTY_DATA_SQL_SIZE = 3;

    public static Vet createVet() {
        Vet vet = new Vet();
        vet.setFirstName("Rick");
        vet.setLastName("lastName");
        vet.setSpecialties(createSpecialties());
        return vet;
    }

    public static Set<Specialty> createSpecialties() {
        Set<Specialty> set = new HashSet<>();
        set.add(createSpecialty());
        return set;
    }

    public static Specialty createSpecialty(){
        Specialty specialty = new Specialty(1L, "petDoctor");
        return specialty;
    }

}
