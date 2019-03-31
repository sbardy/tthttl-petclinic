import {Injectable} from '@angular/core';
import {Owner, OwnerRow, Pet, PetRow} from '../customer-grid/customer.model';
import {HttpClient} from '@angular/common/http';
import {Specialty, Vet, VetRow} from '../vet-grid/vet-model';
import {VisitRequestBody} from '../visit-grid/visit.model';

const BASE_URI = 'http://localhost:8080/';
const OWNERS = BASE_URI + 'owners';
const PETS = BASE_URI + 'pets';
const VETS = BASE_URI + 'vets';
const PET_TYPES = PETS + '/pet-types';
const VET_SPECIALTIES = VETS + '/specialties';
const VISITS = BASE_URI + 'visits';

@Injectable({
    providedIn: 'root'
})
export class RestClient {

    constructor(private httpClient: HttpClient) { }

    public getOwners() {
        return this.httpClient.get(OWNERS);
    }

    public getOwner(ownerId: string) {
        return this.httpClient.get(OWNERS + '/'.concat(ownerId));
    }

    public updateOwner(ownerId: string, ownerToUpdate: Owner) {
        return this.httpClient.put(OWNERS + '/'.concat(ownerId), ownerToUpdate);
    }

    public saveOwner(ownerToSave: Owner) {
        return this.httpClient.post(OWNERS, ownerToSave);
    }

    public deleteOwner(ownerId: string) {
        return this.httpClient.delete(OWNERS + '/'.concat(ownerId));
    }

    public getPets() {
        return this.httpClient.get(PETS);
    }

    public getPet(petId: string) {
        return this.httpClient.get(PETS + '/'.concat(petId));
    }

    public savePet(ownerId: string, petToSave: Pet) {
        return this.httpClient.post(PETS + '/' + 'owner/' + ownerId, petToSave);
    }

    public updatePet(petId: string, petToUpdate: Pet) {
        return this.httpClient.put(PETS + '/'.concat(petId), petToUpdate);
    }

    public deletePet(petId: string) {
        return this.httpClient.delete(PETS + '/'.concat(petId));
    }

    public getPetTypes() {
        return this.httpClient.get(PET_TYPES);
    }

    public getVets() {
        return this.httpClient.get(VETS);
    }

    public getVet(vetId: string) {
        return this.httpClient.get(VETS + '/'.concat(vetId));
    }

    public saveVet(vetToSave: Vet) {
        return this.httpClient.post(VETS, vetToSave);
    }

    public updateVet(vetId: string, vetToUpdate: Vet) {
        return this.httpClient.put(VETS + '/'.concat(vetId), vetToUpdate);
    }

    public deleteVet(vetId: string) {
        return this.httpClient.delete(VETS + '/'.concat(vetId));
    }

    public getSpecialties() {
        return this.httpClient.get(VET_SPECIALTIES);
    }

    public getVisit(visitId: string) {
        return this.httpClient.get(VISITS + '/'.concat(visitId));
    }

    public updateVisit(visitId: string, visitToUpdate: VisitRequestBody) {
        return this.httpClient.put(VISITS + '/'.concat(visitId), visitToUpdate);
    }

    public saveVisit(visitToSave: VisitRequestBody) {
        return this.httpClient.post(VISITS, visitToSave);
    }

    public getVisitWithPets() {
        return this.httpClient.get(VISITS + '/with-pet');
    }

    public deleteVisit(visitId: string) {
        return this.httpClient.delete(VISITS + '/'.concat(visitId));
    }

    mapPetToPetRow(pet: Pet): PetRow {
        const petRow: PetRow = {
            id: pet.id,
            name: pet.name,
            birthdate: pet.birthDate,
            type: pet.type.name,
            owner: pet.owner
        };
        return petRow;
    }

    mapOwnerToRow(owner: Owner): OwnerRow {
        const ownerRow: OwnerRow = {
            id: owner.id,
            firstName: owner.firstName,
            lastName: owner.lastName,
            address: owner.address,
            city: owner.city,
            telephone: owner.telephone,
            pets: this.concatPets(owner.pets)
        };
        return ownerRow;
    }

    mapToVetRow(vet: Vet): VetRow {
        const row: VetRow = {
            id: vet.id,
            firstName: vet.firstName,
            lastName: vet.lastName,
            specialties: this.appendSpecialties(vet.specialties)
        };
        return row;
    }

    concatPets(pets: Pet[]): string {
        let petNames = '';
        pets.forEach((pet, index) => {
            if (index > 0) {
                petNames = petNames.concat(', ' + pet.name);
            } else {
                petNames = petNames.concat(pet.name);
            }
        });
        return petNames;
    }

    appendSpecialties(specialties: Specialty[]) {
        let result = '';
        specialties.forEach((specialty, index) => {
            if (index > 0) {
                result = result.concat(', ' + specialty.name);
            } else {
                result = result.concat(specialty.name);
            }
        });
        return result;
    }

}
