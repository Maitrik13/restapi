package com.service.pet.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.service.pet.dto.User;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	private static final Logger _log = LogManager.getLogger(UserDetailsService.class);

	private static final String GET_USER_END_POINT_URL = "http://localhost:5050/";

	@Autowired
	PasswordEncoder passwordEncoder;

	public UserDetails loadUserByUsername(String username) {
		//seaprate HTTP rest call to User service to fetch user details.
		return new User("maitrik1", passwordEncoder.encode("test@123"));
	}
	
}
