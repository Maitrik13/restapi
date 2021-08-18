package com.user.service.service;

import com.user.service.data.ResponseData;
import com.user.service.dto.User;
import com.user.service.exception.NotFoundException;

public interface LoginService {

	ResponseData validateUserLoginAndGenerateToken(User user) throws NotFoundException;

	ResponseData fetchUserById(String userId) throws NotFoundException;

}
