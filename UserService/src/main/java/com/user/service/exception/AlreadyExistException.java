package com.user.service.exception;

/**
 * @author Maitrik PANCHAL
 * Custom exception, will thrown when an entity already exist in database.
 */
public class AlreadyExistException extends Exception {

	private static final long serialVersionUID = 2729182751718998687L;

	public AlreadyExistException(String message) {
		super(message);
	}

	public AlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
