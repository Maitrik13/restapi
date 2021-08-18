package com.service.pet.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.pet.data.PetRequestData;
import com.service.pet.data.ResponseData;
import com.service.pet.dto.Pet;
import com.service.pet.exception.NotAvailableExcepion;
import com.service.pet.repo.PetRepository;

@Service
public class IPetSerrvice implements PetService {

	private static final Logger _log = LogManager.getLogger(IPetSerrvice.class);

	@Autowired
	private PetRepository petRepository;

	@Override
	public ResponseData addPet(Pet pet) throws com.service.pet.exception.AlreadyExistException {
		long petExist = petRepository.findAllPetByName(pet.getPetName());
		if (petExist > 0) {
			_log.debug("PetName : {} already exist. ", pet.getPetName());
			throw new com.service.pet.exception.AlreadyExistException("Pet name already exist.");
		}
		Pet savedPetDetails = petRepository.save(pet);
		if (null != savedPetDetails) {
			return new ResponseData("Pet details saved successfully.", null);
		}
		return new ResponseData("Fail to perform operation. Please connecr Admin for further assistant.", null);
	}

	@Override
	public ResponseData getAllPets() {
		List<Pet> pets = petRepository.findAll();
		return new ResponseData("Fetched all pets.", pets);
	}

	@Override
	public ResponseData getAvailablePets() {
		List<Pet> availablePets = petRepository.findAvailablePets(true);
		return new ResponseData("Available pets.", availablePets);
	}

	@Override
	public ResponseData getSoldPets() {
		List<Pet> soldPets = petRepository.findAvailablePets(false);
		return new ResponseData("Sold pets.", soldPets);
	}

	@Override
	public ResponseData buyPet(PetRequestData data) throws NotAvailableExcepion {
		Pet isAvailableToBuy = petRepository.checkAvailablePet(data.getPetId());
		if (null == isAvailableToBuy) {
			_log.debug("Pet with id : {}  is not available to buy.", data.getPetId());
			throw new NotAvailableExcepion("Pet is not available to buy.");
		}
		isAvailableToBuy.setOwnedBy(data.getLoggedInUserId());
		isAvailableToBuy.setIsAvailableToBuy(false);
		petRepository.save(isAvailableToBuy);
		return new ResponseData("Pet successfully purchased.", null);
	}

	@Override
	public ResponseData getOwnedPets(PetRequestData data) {
		List<Pet> ownedPets = petRepository.getOwnedPets(data.getLoggedInUserId());
		return new ResponseData("Owned Pets data fetched successfully.", ownedPets);
	}

}
