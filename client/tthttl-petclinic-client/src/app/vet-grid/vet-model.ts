export interface Specialty {
    id: string;
    name: string;
}

export interface Vet {
    firstName: string;
    lastName: string;
    specialties: Specialty[];
}

export interface VetRow {
    firstName: string;
    lastName: string;
    specialties: string;
}
