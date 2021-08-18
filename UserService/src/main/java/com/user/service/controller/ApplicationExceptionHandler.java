package com.user.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.user.service.data.ResponseData;
import com.user.service.exception.AlreadyExistException;
import com.user.service.exception.NotFoundException;

/**
 * @author Maitrik PANCHAL
 * Global exception handler class to handle exception thrown from controller.
 */
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseData> handleGlobalExceptions(Exception ex, WebRequest request) {
		return new ResponseEntity<ResponseData>(new ResponseData(ex.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseData> handleNotFoundException(NotFoundException ex, WebRequest request) {
		return new ResponseEntity<ResponseData>(new ResponseData(ex.getMessage(), null), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<ResponseData> handleAlreadyExistException(AlreadyExistException ex, WebRequest request) {
		return new ResponseEntity<ResponseData>(new ResponseData(ex.getMessage(), null), HttpStatus.ALREADY_REPORTED);
	}

}
