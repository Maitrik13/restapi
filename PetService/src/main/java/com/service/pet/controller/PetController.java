package com.service.pet.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.pet.data.PetRequestData;
import com.service.pet.data.ResponseData;
import com.service.pet.dto.Pet;
import com.service.pet.exception.AlreadyExistException;
import com.service.pet.exception.NotAvailableExcepion;
import com.service.pet.service.PetService;

/**
 * @author Maitrik PANCHAL
 * COntroller to handle pet requests. If any authentication failed controller will not serve and HTTP request.
 */
@RequestMapping("/api/v1/pet")
@RestController
public class PetController {

	private static final Logger _log = LogManager.getLogger(PetController.class);

	@Autowired
	private PetService petService;

	@PostMapping("/add")
	public ResponseEntity<ResponseData> addPet(@RequestBody Pet pet) throws AlreadyExistException {
		_log.debug("Requested data to add pet : {} ", pet);
		ResponseData response = petService.addPet(pet);
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseData> getAllPets() {
		ResponseData response = petService.getAllPets();
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}

	@GetMapping("/getAvailable")
	public ResponseEntity<ResponseData> getAvailablePetsToBuy() {
		ResponseData response = petService.getAvailablePets();
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}

	@GetMapping("/getSold")
	public ResponseEntity<ResponseData> getSoldPets() {
		ResponseData response = petService.getSoldPets();
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}

	@PostMapping("owned")
	public ResponseEntity<ResponseData> getOwnedpets(@RequestBody PetRequestData data){
		ResponseData response = petService.getOwnedPets(data);
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}
	
	@PostMapping("/buy")
	public ResponseEntity<ResponseData> buyPet(@RequestBody PetRequestData data) throws NotAvailableExcepion {
		_log.debug("Request data to buy pet : {} ", data);
		ResponseData response = petService.buyPet(data);
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}

}
