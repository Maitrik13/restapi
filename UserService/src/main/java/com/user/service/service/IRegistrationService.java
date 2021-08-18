package com.user.service.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.service.data.ResponseData;
import com.user.service.dto.User;
import com.user.service.exception.AlreadyExistException;
import com.user.service.repo.UserRepository;

@Service
public class IRegistrationService implements RegistrationService {

	private static final Logger _log = LogManager.getLogger(IRegistrationService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseData registerUser(User user) throws AlreadyExistException {
		long userCountByUserName = userRepository.findAllUserByUserName(user.getUsername());
		if (userCountByUserName > 0) {
			_log.debug("Username : {} already exist. ", user.getUsername());
			throw new AlreadyExistException("Username already exist.");
		}
		// validate data
		User savedUserDetail = userRepository.save(user);
		if (null != savedUserDetail) {
			return new ResponseData("User registered successfully.", null);
		}
		return new ResponseData("Fail to perform operation. Please connecr Admin for further assistant.", null);
	}

}
