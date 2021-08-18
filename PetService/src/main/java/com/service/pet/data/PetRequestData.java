package com.service.pet.data;

/**
 * @author Maitrik PANCHAL
 * Incoming pet request data
 */
public class PetRequestData {

	private String loggedInUserId;

	private String petId;
	
	public PetRequestData() {}

	public PetRequestData(String loggedInUserId, String petId) {
		super();
		this.loggedInUserId = loggedInUserId;
		this.petId = petId;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

	@Override
	public String toString() {
		return "BuyPetRequestData [loggedInUserId=" + loggedInUserId + ", petId=" + petId + "]";
	}

}
