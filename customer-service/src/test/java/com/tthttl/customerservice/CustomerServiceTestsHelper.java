package com.tthttl.customerservice;

import com.tthttl.customerservice.owner.model.Owner;
import com.tthttl.customerservice.pet.model.Pet;
import com.tthttl.customerservice.pet.model.PetType;

import java.time.LocalDate;

public class CustomerServiceTestsHelper {

    public static final Long ID = 1L;
    public static final int DATA_SQL_OWNERS_SIZE = 6;
    public static final int DATA_SQL_PETS_SIZE = 13;

    private CustomerServiceTestsHelper() {
    }

    public static Owner createOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Morty");
        owner.setLastName("lastName");
        owner.setAddress("address");
        owner.setCity("city");
        owner.setTelephone("1234567");
        Pet dog = createDog();
        dog.setOwner(owner);
        owner.addPet(dog);
        return owner;
    }

    public static Pet createDog() {
        Pet dog = new Pet();
        dog.setName("Snowball");
        dog.setBirthDate(LocalDate.of(2000, 1, 1));
        dog.setType(new PetType(2L, "dog"));
        return dog;
    }

}
