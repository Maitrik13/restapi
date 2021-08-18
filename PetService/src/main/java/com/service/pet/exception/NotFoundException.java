package com.service.pet.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -4376538458775316210L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
