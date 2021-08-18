package com.user.service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.data.ResponseData;
import com.user.service.dto.User;
import com.user.service.exception.NotFoundException;
import com.user.service.service.LoginService;

/**
 * @author Maitrik PANCHAL
 * Controller to provide signin and authentication service. on successful authentication will send JWT token.
 */
@RequestMapping("/api/v1/user/")
@RestController
public class SignInController {

	private static final Logger _log = LogManager.getLogger(SignInController.class);

	@Autowired
	private LoginService loginService;

	@PostMapping("login")
	public ResponseEntity<ResponseData> loginUser(@RequestBody User user) throws NotFoundException {
		_log.debug("Login request for user : {} ", user);
		ResponseData response = loginService.validateUserLoginAndGenerateToken(user);
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);

	}

	@GetMapping("get/{uuid}")
	public ResponseEntity<ResponseData> getUserDetails(@PathVariable(required = true, name = "uuid") String userId)
			throws NotFoundException {
		_log.debug("Fetching user details with id : {} ", userId);
		ResponseData response = loginService.fetchUserById(userId);
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}

}
