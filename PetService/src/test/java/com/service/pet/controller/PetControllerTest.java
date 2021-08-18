package com.service.pet.controller;

import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.service.pet.data.PetRequestData;
import com.service.pet.data.ResponseData;
import com.service.pet.dto.Pet;
import com.service.pet.exception.NotAvailableExcepion;
import com.service.pet.repo.PetRepository;
import com.service.pet.service.PetService;

@WebMvcTest
class PetControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	PetService petService;

	@MockBean
	private PetRepository petRepository;

	private static final String ownedByUserUUID = UUID.randomUUID().toString();

	Pet petRec1 = new Pet(UUID.randomUUID().toString(), "MyPet1", 15, "Abd1", true, ownedByUserUUID, new Date(),
			new Date());
	Pet petRec2 = new Pet(UUID.randomUUID().toString(), "MyPet2", 25, "Abd2", false, ownedByUserUUID, new Date(),
			new Date());
	Pet petRec3 = new Pet(UUID.randomUUID().toString(), "MyPet3", 35, "Abd3", true, ownedByUserUUID, new Date(),
			new Date());
	Pet petRec4 = new Pet(UUID.randomUUID().toString(), "MyPet4", 45, "Abd4", true, ownedByUserUUID, new Date(),
			new Date());

	@Test
	public void getAllPetRecords() throws Exception {
		List<Pet> petList = new ArrayList<Pet>(Arrays.asList(petRec1, petRec2, petRec3, petRec4));

		Mockito.when(petService.getAllPets()).thenReturn(new ResponseData("", petList));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pet/getAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(("data"), hasSize(4)));
	}

	@Test
	public void getAvailablePets() throws Exception {

		List<Pet> availablepets = new ArrayList<Pet>(Arrays.asList(petRec1, petRec3, petRec4));

		Mockito.when(petService.getAvailablePets()).thenReturn(new ResponseData("", availablepets));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pet/getAvailable").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(("data"), hasSize(3)));
	}

	@Test
	public void getSoldPets() throws Exception {

		List<Pet> soldPets = new ArrayList<Pet>(Arrays.asList(petRec2));

		Mockito.when(petService.getSoldPets()).thenReturn(new ResponseData("", soldPets));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pet/getSold").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(("data"), hasSize(1)));
	}

	@Test
	public void buyPetsNotAvailable() throws Exception {
		String uuid = UUID.randomUUID().toString();
		Pet pet = new Pet(uuid, "AddPet1", 26, "Abds", true, ownedByUserUUID, null, null);

		Mockito.when(petRepository.checkAvailablePet(uuid)).thenReturn(null);

		Mockito.when(petService.buyPet(new PetRequestData(ownedByUserUUID, uuid)))
				.thenThrow(NotAvailableExcepion.class);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pet/buy").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void buyPet() throws Exception {
		String uuid = UUID.randomUUID().toString();
		Pet pet = new Pet(uuid, "AddPet1", 26, "Abds", true, ownedByUserUUID, null, null);

		Mockito.when(petRepository.checkAvailablePet(uuid)).thenReturn(pet);

		Mockito.when(petService.buyPet(new PetRequestData(ownedByUserUUID, uuid)))
				.thenReturn(new ResponseData("", pet));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pet/buy").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void addPet() throws Exception {
		String uuid = UUID.randomUUID().toString();
		Pet pet = new Pet(uuid, "AddPet1", 26, "Abds", true, ownedByUserUUID, null, null);

		Mockito.when(petService.addPet(pet)).thenReturn(new ResponseData("", pet));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pet/add").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(pet.toString()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
