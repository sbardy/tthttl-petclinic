import {PetType} from '../customer-grid/customer.model';

export interface PetResponse {
    name: string;
    birthdate: string;
    petType: PetType;
    owner: string;
}

export interface VisitWithPet {
    id: string;
    date: string;
    description: string;
    pet: PetResponse;
}

export interface Visit {
    id: string;
    date: string;
    description: string;
    petId: string;
}

export interface VisitRow {
    id: string;
    date: string;
    description: string;
    pet: string;
    owner: string;
}

export interface VisitRequestBody {
    date: string;
    description: string;
    petId: string;
}

