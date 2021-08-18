package com.user.service.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.service.data.ResponseData;
import com.user.service.dto.User;
import com.user.service.exception.NotFoundException;
import com.user.service.jwt.JwtTokenGeneratorUtil;
import com.user.service.repo.UserRepository;

@Service
public class ILoginService implements LoginService {

	private static final Logger _log = LogManager.getLogger(ILoginService.class);

	@Autowired
	private JwtTokenGeneratorUtil jwtTokengenrator;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseData validateUserLoginAndGenerateToken(User user) throws NotFoundException {
		User existUser = userRepository.validateExistUser(user.getUsername(), user.getPassword());
		if (null != existUser) {
			String generatedToken = jwtTokengenrator.generateToken(user);
			return new ResponseData("Login successfully.", generatedToken);
		} else {
			_log.debug("User not exist with username : {} ", user.getUsername());
			throw new NotFoundException("User details not found.");
		}
	}

	@Override
	public ResponseData fetchUserById(String userId) throws NotFoundException {

		Optional<User> userDetails = userRepository.findById(userId);

		if (null == userDetails || !userDetails.isPresent()) {
			_log.debug("No user details exist with userId : {} ", userId);
			throw new NotFoundException("No user details exist.");
		}

		return new ResponseData("User details fetched successfully.", userDetails.get());
	}

}
