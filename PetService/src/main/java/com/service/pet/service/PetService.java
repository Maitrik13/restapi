package com.service.pet.service;

import com.service.pet.data.PetRequestData;
import com.service.pet.data.ResponseData;
import com.service.pet.dto.Pet;
import com.service.pet.exception.AlreadyExistException;
import com.service.pet.exception.NotAvailableExcepion;

public interface PetService {

	ResponseData addPet(Pet pet) throws AlreadyExistException;

	ResponseData getAllPets();

	ResponseData getAvailablePets();

	ResponseData getSoldPets();

	ResponseData buyPet(PetRequestData data) throws NotAvailableExcepion;

	ResponseData getOwnedPets(PetRequestData data);

}
