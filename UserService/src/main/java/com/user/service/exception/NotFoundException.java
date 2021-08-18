package com.user.service.exception;

/**
 * @author Maitrik PANCHAL
 * Custom exception, will thrown when an entity not exist in database.
 */
public class NotFoundException extends Exception {

	private static final long serialVersionUID = -4376538458775316210L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
