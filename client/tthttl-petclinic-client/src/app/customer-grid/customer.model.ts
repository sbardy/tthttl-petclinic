export interface Pet {
    name: string;
    birthDate: string;
    petType: PetType;
    owner: string;
}

export interface Owner {
    firstName: string;
    lastName: string;
    address: string;
    city: string;
    telephone: string;
    pets: Pet[];
}

export interface OwnerRow {
    firstName: string;
    lastName: string;
    address: string;
    city: string;
    telephone: string;
    pets: string;
}

export interface PetType {
    id: string;
    name: string;
}

export interface PetRow {
    name: string;
    birthdate: string;
    type: string;
    owner: string;
}
