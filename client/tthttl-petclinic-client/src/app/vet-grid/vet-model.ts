export interface Specialty {
    id: string;
    name: string;
}

export interface Vet {
    id: string;
    firstName: string;
    lastName: string;
    specialties: Specialty[];
}

export interface VetRow {
    id: string;
    firstName: string;
    lastName: string;
    specialties: string;
}
