package com.service.pet.exception;

public class NotAvailableExcepion extends Exception {

	private static final long serialVersionUID = 2466333785357732638L;

	public NotAvailableExcepion(String message) {
		super(message);
	}

	public NotAvailableExcepion(String message, Throwable cause) {
		super(message, cause);
	}

}
