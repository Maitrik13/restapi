package com.service.pet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.pet.dto.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {

	@Query("select count(*) from Pet p where p.petName = :petName")
	public long findAllPetByName(@Param("petName") String petName);

	@Query("select p from Pet p where p.isAvailableToBuy = :available")
	public List<Pet> findAvailablePets(@Param("available") boolean available);

	@Query("select p from Pet p where p.UUID = :petId  AND p.isAvailableToBuy = true")
	public Pet checkAvailablePet(@Param("petId") String petId);

	@Query("select p from Pet p where p.ownedBy = :ownedBy")
	public List<Pet> getOwnedPets(@Param("ownedBy") String ownedBy);

}
