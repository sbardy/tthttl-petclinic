export interface Pet {
    id: string;
    name: string;
    birthDate: string;
    type: PetType;
    owner: string;
}

export interface Owner {
    id: string;
    firstName: string;
    lastName: string;
    address: string;
    city: string;
    telephone: string;
    pets: Pet[];
}

export interface OwnerRow {
    id: string;
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
    id: string;
    name: string;
    birthdate: string;
    type: string;
    owner: string;
}
