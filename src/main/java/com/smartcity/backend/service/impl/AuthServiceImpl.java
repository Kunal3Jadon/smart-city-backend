package com.smartcity.backend.service.impl;

import lombok.*;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartcity.backend.dto.LoginRequest;
import com.smartcity.backend.dto.LoginResponse;
import com.smartcity.backend.dto.RegisterRequest;
import com.smartcity.backend.entity.Role;
import com.smartcity.backend.entity.User;
import com.smartcity.backend.repository.UserRepository;
import com.smartcity.backend.security.JwtService;
import com.smartcity.backend.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	@Override
	public User register(RegisterRequest request) {
		User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CITIZEN)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
	}

	@Override
	public LoginResponse login(LoginRequest request) {

		User user = userRepository
	            .findByEmail(request.getEmail())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
	        throw new RuntimeException("Invalid password");
	    }
	    
	    LoginResponse response=LoginResponse.builder()
	    		.id(user.getId())
	    		.email(user.getEmail())
	    		.name(user.getName())
	    		.role(user.getRole())
	    		.token(jwtService.generateToken(user.getEmail()))
	    		.build();
	    return response;
	}
}
