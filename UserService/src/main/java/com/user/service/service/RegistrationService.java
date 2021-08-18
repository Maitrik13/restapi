package com.user.service.service;

import com.user.service.data.ResponseData;
import com.user.service.dto.User;
import com.user.service.exception.AlreadyExistException;

public interface RegistrationService {

	ResponseData registerUser(User user) throws AlreadyExistException;

}
