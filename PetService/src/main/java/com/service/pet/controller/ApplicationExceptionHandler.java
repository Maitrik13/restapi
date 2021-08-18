package com.service.pet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.service.pet.data.ResponseData;
import com.service.pet.exception.AlreadyExistException;
import com.service.pet.exception.NotAvailableExcepion;
import com.service.pet.exception.NotFoundException;

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
	
	@ExceptionHandler(NotAvailableExcepion.class)
	public ResponseEntity<ResponseData> handleNotAvailableException(NotAvailableExcepion ex, WebRequest request) {
		return new ResponseEntity<ResponseData>(new ResponseData(ex.getMessage(), null), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<ResponseData> handleAlreadyExistException(AlreadyExistException ex, WebRequest request) {
		return new ResponseEntity<ResponseData>(new ResponseData(ex.getMessage(), null), HttpStatus.ALREADY_REPORTED);
	}

}
