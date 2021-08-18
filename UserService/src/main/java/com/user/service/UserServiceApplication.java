package com.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Maitrik PANCHAL 
 * micro service build to manage user like registration,
 *         login and authentication.
 */
@SpringBootApplication
public class UserServiceApplication {

	private static final Logger _log = LogManager.getLogger(UserServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		_log.info("User service started successfully.");
	}

}
