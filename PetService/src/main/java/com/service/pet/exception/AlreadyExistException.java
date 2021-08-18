package com.service.pet.exception;

public class AlreadyExistException extends Exception {

	private static final long serialVersionUID = 2729182751718998687L;

	public AlreadyExistException(String message) {
		super(message);
	}

	public AlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
