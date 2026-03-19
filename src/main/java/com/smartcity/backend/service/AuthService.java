package com.smartcity.backend.service;

import com.smartcity.backend.dto.LoginRequest;
import com.smartcity.backend.dto.LoginResponse;
import com.smartcity.backend.dto.RegisterRequest;
import com.smartcity.backend.entity.User;

public interface AuthService {
	
	 User register(RegisterRequest request);

	 LoginResponse login(LoginRequest request);
}
