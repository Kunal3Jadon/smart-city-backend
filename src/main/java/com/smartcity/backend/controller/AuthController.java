package com.smartcity.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.smartcity.backend.dto.LoginRequest;
import com.smartcity.backend.dto.LoginResponse;
import com.smartcity.backend.dto.RegisterRequest;
import com.smartcity.backend.entity.User;
import com.smartcity.backend.service.AuthService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

    @PostMapping("/register")
    public User register(@Valid  @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid  @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}
