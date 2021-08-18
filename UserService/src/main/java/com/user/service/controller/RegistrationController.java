package com.user.service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.data.ResponseData;
import com.user.service.dto.User;
import com.user.service.exception.AlreadyExistException;
import com.user.service.service.RegistrationService;

/**
 * @author Maitrik PANCHAL
 * Controller to register user
 */
@RequestMapping("/api/v1/user/")
@RestController
public class RegistrationController {

	private static final Logger _log = LogManager.getLogger(RegistrationController.class);
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseData> registerUser(@RequestBody User user) throws AlreadyExistException{
		_log.debug("Requested data for user registration : {} ",user);
		ResponseData response = registrationService.registerUser(user);
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}
	
}
